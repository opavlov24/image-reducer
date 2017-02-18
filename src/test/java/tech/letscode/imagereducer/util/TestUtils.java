package tech.letscode.imagereducer.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public final class TestUtils
{
    private static final String ROOT_DIRECTORY = "image";

    private TestUtils()
    {
        super();
    }

    public static String rootDirectory()
    {
        URL resource = TestUtils.class.getClassLoader().getResource(ROOT_DIRECTORY);
        if (resource == null)
        {
            throw new IllegalStateException("Root directory ('" + ROOT_DIRECTORY + "') has not been found");
        }
        return resource.getFile();
    }

    public static File loadOriginalFile()
    {
        URL resource = TestUtils.class.getClassLoader().getResource(ROOT_DIRECTORY + "/notReducedImage.jpeg");
        if (resource == null)
        {
            throw new IllegalStateException("Original file has not been found in the test resources");
        }
        return new File(resource.getFile());
    }

    public static File temporaryFile() throws IOException
    {
        File tempFile = Files.createTempFile(null, null).toFile();
        tempFile.deleteOnExit();
        return tempFile;
    }
}
