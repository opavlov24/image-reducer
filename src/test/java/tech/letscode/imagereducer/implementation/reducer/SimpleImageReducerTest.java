package tech.letscode.imagereducer.implementation.reducer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;
import static tech.letscode.imagereducer.util.TestUtils.loadOriginalFile;
import static tech.letscode.imagereducer.util.TestUtils.temporaryFile;

public class SimpleImageReducerTest
{
    @Test
    public void reduce() throws URISyntaxException, IOException
    {
        SimpleImageReducer imageReducer = new SimpleImageReducer();
        File originalImage = loadOriginalFile();
        long originalSize = originalImage.length();
        File compressedImage = temporaryFile();
        imageReducer.reduce(originalImage, compressedImage, 0.1f);
        long compressedSize = compressedImage.length();
        assertTrue(compressedSize != 0);
        assertTrue(compressedSize < originalSize);
    }
}
