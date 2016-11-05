package tech.letscode.imagereducer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

public class SimpleImageReducerTest
{
    @Test
    public void reduce() throws URISyntaxException, IOException
    {
        SimpleImageReducer imageReducer = new SimpleImageReducer();
        File originalImage = loadTestImage();
        long originalSize = originalImage.length();
        File compressedImage = createOutputImageFile();
        imageReducer.reduce(originalImage, compressedImage, 0.1f);
        long compressedSize = compressedImage.length();
        assertTrue(compressedSize != 0);
        assertTrue(compressedSize < originalSize);
    }

    private File loadTestImage() throws URISyntaxException
    {
        return new File(getClass().getClassLoader().getResource("image/notReducedImage.jpeg").toURI());
    }

    private File createOutputImageFile() throws IOException
    {
        File outImage = Files.createTempFile(null, null).toFile();
        outImage.deleteOnExit();
        return outImage;
    }

}
