package tech.letscode.imagereducer.implementation.resolver;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tech.letscode.imagereducer.util.DirectoryUtils.pathWithoutFilename;
import static tech.letscode.imagereducer.util.TestUtils.loadOriginalFile;

public class SimpleOutputFileResolverTest
{
    private final String OUT_DIRECTORY = "out";

    private File outputFile;

    @Test
    public void createNewFileThatPathBasedOnPathToOriginalFileAndRelativeDirectoryName()
    {
        SimpleOutputFileResolver outputFileResolver = new SimpleOutputFileResolver(OUT_DIRECTORY);
        File originalFile = loadOriginalFile();
        this.outputFile = outputFileResolver.resolveFileBasedOnOriginal(originalFile);
        assertTrue(this.outputFile.isFile());
        assertEquals(expectedPath(originalFile), this.outputFile.getAbsolutePath());
    }

    private String expectedPath(File originalFile)
    {
        return pathWithoutFilename(originalFile) + separator + OUT_DIRECTORY + separator + originalFile.getName();
    }

    @After
    public void clean()
    {
        if (this.outputFile != null) {
            this.outputFile.delete();
            this.outputFile.getParentFile().delete();
        }
    }
}
