package tech.letscode.imagereducer;

import tech.letscode.imagereducer.implementation.CommandLineConfiguration;
import tech.letscode.imagereducer.implementation.reducer.SimpleImageReducer;
import tech.letscode.imagereducer.implementation.resolver.SimpleOutputFileResolver;
import tech.letscode.imagereducer.implementation.scanner.SimpleDirectoryScanner;
import tech.letscode.imagereducer.implementation.view.ConsoleOutputView;

public class Application
{
    private static final DirectoryScanner DIRECTORY_SCANNER = new SimpleDirectoryScanner();

    private static final ImageReducer IMAGE_REDUCER = new SimpleImageReducer();

    private static final OutputFileResolver OUTPUT_FILE_RESOLVER = new SimpleOutputFileResolver("compressed");

    private static final OutputView OUTPUT_VIEW = new ConsoleOutputView();

    public static void main(String[] args)
    {
        ImageReducerConfiguration configuration = CommandLineConfiguration.build(args);
        if (configuration != null) {
            ImageReducerApplication application = new ImageReducerApplication(DIRECTORY_SCANNER,
                                                                              IMAGE_REDUCER,
                                                                              OUTPUT_FILE_RESOLVER,
                                                                              configuration,
                                                                              OUTPUT_VIEW);
            application.run();
        }
    }
}
