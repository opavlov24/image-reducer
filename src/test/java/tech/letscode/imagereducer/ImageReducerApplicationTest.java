package tech.letscode.imagereducer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.letscode.imagereducer.exception.ImageReducerApplicationException;
import tech.letscode.imagereducer.exception.ImageReducerException;
import tech.letscode.imagereducer.exception.OutputFileResolverException;
import tech.letscode.imagereducer.implementation.scanner.StubDirectoryScanner;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static tech.letscode.imagereducer.util.TestUtils.*;

public class ImageReducerApplicationTest
{
    private File outputFile;

    private DirectoryScanner directoryScanner;

    private ImageReducer imageReducer;

    private OutputFileResolver outputFileResolver;

    private OutputView outputView;

    @Before
    public void setup() throws IOException
    {
        this.directoryScanner = new StubDirectoryScanner(loadOriginalFile());
        this.imageReducer = mock(ImageReducer.class);
        this.outputFileResolver = mock(OutputFileResolver.class);
        this.outputFile = temporaryFile();
        when(outputFileResolver.resolveFileBasedOnOriginal(any())).thenReturn(this.outputFile);
        this.outputView = mock(OutputView.class);
    }

    @After
    public void clear()
    {
        deleteQuietly(this.outputFile);
    }

    @Test
    public void run() throws IOException
    {
        ImageReducerApplication app = createApp(configure(true));
        app.run();
        verify(outputFileResolver).resolveFileBasedOnOriginal(any());
        verify(imageReducer).reduce(any(), any(), anyFloat());
    }

    @Test
    public void whenOutputFileResolverExceptionThrownAndExceptionSuppressedThenOutputFileCannotBeCreatedForCalled()
    {
        when(this.outputFileResolver.resolveFileBasedOnOriginal(any())).thenThrow(OutputFileResolverException.class);
        ImageReducerApplication app = createApp(configure(true));
        app.run();
        verify(this.outputView).outputFileCannotBeCreatedFor(any());
    }

    @Test
    public void whenImageReducerExceptionThrownAndExceptionSuppressedThenOriginalImageCannotBeReducedCalled()
    {
        doThrow(ImageReducerException.class).when(this.imageReducer).reduce(any(), any(), anyFloat());
        ImageReducerApplication app = createApp(configure(true));
        app.run();
        verify(this.outputView).originalImageCannotBeReduced(any());
    }

    @Test
    public void whenUnexpectedExceptionThrownAndExceptionSuppressedThenUnexpectedErrorOccurredCalled()
    {
        doThrow(RuntimeException.class).when(this.imageReducer).reduce(any(), any(), anyFloat());
        ImageReducerApplication app = createApp(configure(true));
        app.run();
        verify(this.outputView).unexpectedErrorHasBeenOccurred(any());

    }

    @Test(expected = ImageReducerApplicationException.class)
    public void whenOutputFileResolverExceptionThrownThenImageReducerApplicationExceptionThrown()
    {
        when(this.outputFileResolver.resolveFileBasedOnOriginal(any())).thenThrow(OutputFileResolverException.class);
        ImageReducerApplication app = createApp(configure(false));
        app.run();
    }

    @Test(expected = ImageReducerApplicationException.class)
    public void whenImageReducerExceptionThrownThenImageReducerApplicationExceptionThrown()
    {
        doThrow(ImageReducerException.class).when(this.imageReducer).reduce(any(), any(), anyFloat());
        ImageReducerApplication app = createApp(configure(false));
        app.run();
    }

    @Test(expected = ImageReducerApplicationException.class)
    public void whenUnexpectedExceptionThrownThenImageReducerApplicationExceptionThrown()
    {
        doThrow(RuntimeException.class).when(this.imageReducer).reduce(any(), any(), anyFloat());
        ImageReducerApplication app = createApp(configure(false));
        app.run();
    }

    private ImageReducerApplication createApp(ImageReducerConfiguration configuration)
    {
        return new ImageReducerApplication(this.directoryScanner,
                                           this.imageReducer,
                                           this.outputFileResolver,
                                           configuration,
                                           this.outputView);
    }

    private ImageReducerConfiguration configure(boolean suppressException)
    {
        ImageReducerConfiguration configuration = new ImageReducerConfiguration(rootDirectory());
        configuration.setRecursively(true);
        configuration.setSuppressException(suppressException);
        return configuration;
    }
}
