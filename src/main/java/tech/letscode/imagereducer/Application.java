package tech.letscode.imagereducer;

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
