package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

import static org.apache.commons.io.FileUtils.deleteQuietly;

public class ImageReducerApplication implements DirectoryListener
{
    private final DirectoryScanner directoryScanner;

    private final ImageReducer imageReducer;

    private final OutputFileResolver outputFileResolver;

    private final ImageReducerConfiguration configuration;

    private final OutputView outputView;

    public ImageReducerApplication(@Nonnull DirectoryScanner directoryScanner,
                                   @Nonnull ImageReducer imageReducer,
                                   @Nonnull OutputFileResolver outputFileResolver,
                                   @Nonnull ImageReducerConfiguration configuration,
                                   @Nonnull OutputView outputView)
    {
        super();
        this.directoryScanner = directoryScanner;
        this.imageReducer = imageReducer;
        this.outputFileResolver = outputFileResolver;
        this.configuration = configuration;
        this.outputView = outputView;
    }

    public void run()
    {
        String rootDirectory = this.configuration.rootDirectory();
        boolean recursively = this.configuration.recursively();
        String[] extensions = this.configuration.extensions();
        this.directoryScanner.addDirectoryListener(this);
        this.directoryScanner.scan(rootDirectory, recursively, extensions);
    }

    @Override
    public void acceptFile(@Nonnull File originalFile)
    {
        File outputFile = null;
        try {
            outputFile = this.outputFileResolver.resolveFileBasedOnOriginal(originalFile);
            imageReducer.reduce(originalFile, outputFile, this.configuration.quality());
            this.outputView.imageReduced(originalFile);
        } catch (Exception e) {
            treatException(e, originalFile);
            if (outputFile != null) {
                deleteQuietly(outputFile);
                deleteQuietly(outputFile.getParentFile());
            }
        }
    }

    private void treatException(Exception exception, File originalFile)
    {
        if (this.configuration.suppressException()) {
            if (exception instanceof OutputFileResolverException) {
                this.outputView.outputFileCannotBeCreatedFor(originalFile);
            } else if (exception instanceof ImageReducerException) {
                this.outputView.originalImageCannotBeReduced(originalFile);
            } else {
                this.outputView.unexpectedErrorHasBeenOccurred(exception);
            }
        } else {
            throw new ImageReducerApplicationException("An error has been occurred", exception);
        }
    }
}
