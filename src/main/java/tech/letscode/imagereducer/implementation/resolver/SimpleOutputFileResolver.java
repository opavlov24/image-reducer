package tech.letscode.imagereducer.implementation.resolver;

import org.apache.commons.lang3.Validate;
import tech.letscode.imagereducer.OutputFileResolver;
import tech.letscode.imagereducer.exception.OutputFileResolverException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

import static tech.letscode.imagereducer.util.DirectoryUtils.pathWithoutFilename;

public class SimpleOutputFileResolver implements OutputFileResolver
{
    private final String outputDirectory;

    public SimpleOutputFileResolver(@Nonnull String outputDirectory)
    {
        super();
        Validate.notNull(outputDirectory, "outputDirectory is required");
        this.outputDirectory = outputDirectory;
    }

    @Override
    public File resolveFileBasedOnOriginal(@Nonnull File originalFile)
    {
        Validate.notNull(originalFile, "originalFile is required");
        File outputFile = new File(buildPathToOutputFile(originalFile));
        try
        {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        } catch (IOException e)
        {
            throw new OutputFileResolverException("IO Exception has been occurred while creating output file", e);
        }
        return outputFile;
    }

    private String buildPathToOutputFile(File originalFile)
    {
        String pathToOriginalFile = pathWithoutFilename(originalFile);
        return pathToOriginalFile + File.separator + this.outputDirectory + File.separator +
        originalFile.getName();
    }
}
