package tech.letscode.imagereducer.implementation;

import org.apache.commons.cli.*;
import tech.letscode.imagereducer.ImageReducerConfiguration;

public class CommandLineConfiguration
{
    private static final String ROOT_DIRECTORY_OPT = "directory";

    private static final String RECURSIVELY_OPT = "recursively";

    private static final String QUALITY_OPT = "quality";

    private static final String SUPPRESS_OPT = "suppress";

    private static final String EXTENSION_OPT = "extension";

    private static final String APPLICATION_NAME = "image-reducer";

    public static ImageReducerConfiguration build(String[] args)
    {
        CommandLineParser parser = new DefaultParser();
        Options options = options();
        try
        {
            CommandLine cl = parser.parse(options, args);
            return buildConfiguration(cl);
        } catch (Exception e)
        {
            printHelp(options);
        }
        return null;
    }

    private static ImageReducerConfiguration buildConfiguration(CommandLine cl)
    {
        String rootDirectory = cl.getOptionValue(ROOT_DIRECTORY_OPT);
        ImageReducerConfiguration configuration = new ImageReducerConfiguration(rootDirectory);
        configuration.setRecursively(cl.hasOption(RECURSIVELY_OPT));
        configuration.setSuppressException(cl.hasOption(SUPPRESS_OPT));
        if (cl.hasOption(QUALITY_OPT))
        {
            float quality = Float.parseFloat(cl.getOptionValue(QUALITY_OPT));
            configuration.setQuality(quality);
        }
        configuration.setExtensions(cl.getOptionValues(EXTENSION_OPT));
        return configuration;
    }

    private static Options options()
    {
        Options options = new Options();
        options.addOption("r", RECURSIVELY_OPT, false, "scan directories recursively (default is false)");
        options.addOption("q", QUALITY_OPT, true, "compress quality (min: 0, max: 1), default value is 0.5");
        options.addOption("s", SUPPRESS_OPT, false, "suppress all errors (default value is false)");
        Option directoryOption = Option.builder("d")
                                       .longOpt(ROOT_DIRECTORY_OPT)
                                       .hasArg()
                                       .required()
                                       .desc("path to the starting point of scanning")
                                       .build();
        Option extensionOption = Option.builder("e")
                                       .longOpt(EXTENSION_OPT)
                                       .hasArgs()
                                       .required()
                                       .desc("file's extensions to compress")
                                       .build();
        options.addOption(directoryOption);
        options.addOption(extensionOption);
        return options;
    }

    private static void printHelp(Options options)
    {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(APPLICATION_NAME, options, true);
    }
}
