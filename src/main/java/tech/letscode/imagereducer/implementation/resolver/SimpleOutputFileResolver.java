package tech.letscode.imagereducer.implementation.resolver;

import tech.letscode.imagereducer.OutputFileResolver;
import tech.letscode.imagereducer.exception.OutputFileResolverException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

import static tech.letscode.imagereducer.util.DirectoryUtils.pathWithoutFilename;

public class SimpleOutputFileResolver implements OutputFileResolver
{
    private final String innerDirectoryName;

    public SimpleOutputFileResolver(@Nonnull String innerDirectoryName)
    {
        super();
        this.innerDirectoryName = innerDirectoryName;
    }

    @Override
    public File resolveFileBasedOnOriginal(File originalFile)
    {
        File outputFile = new File(buildPathToOutputFile(originalFile));
        try {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new OutputFileResolverException("IO Exception has been occurred while creating output file", e);
        }
        return outputFile;
    }

    private String buildPathToOutputFile(File originalFile)
    {
        String pathToOriginalFile = pathWithoutFilename(originalFile);
        return pathToOriginalFile + File.separator + this.innerDirectoryName + File.separator +
        originalFile.getName();
    }
}
