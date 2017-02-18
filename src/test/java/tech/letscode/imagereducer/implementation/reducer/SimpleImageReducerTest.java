package tech.letscode.imagereducer.implementation.reducer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static tech.letscode.imagereducer.util.TestUtils.loadOriginalFile;
import static tech.letscode.imagereducer.util.TestUtils.temporaryFile;

public class SimpleImageReducerTest
{
    private static final float QUALITY = 0.1f;

    private static final float WRONG_QUALITY_MIN = -0.1f;

    private static final float WRONG_QUALITY_MAX = 1.1f;

    @Test
    public void reduce() throws URISyntaxException, IOException
    {
        SimpleImageReducer imageReducer = new SimpleImageReducer();
        File originalImage = loadOriginalFile();
        long originalSize = originalImage.length();
        File compressedImage = temporaryFile();
        imageReducer.reduce(originalImage, compressedImage, QUALITY);
        long compressedSize = compressedImage.length();
        assertTrue(compressedSize != 0);
        assertTrue(compressedSize < originalSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void qualityCannotBeLessThanZero()
    {
        SimpleImageReducer imageReducer = new SimpleImageReducer();
        File originalImage = mock(File.class);
        File compressedImage = mock(File.class);
        imageReducer.reduce(originalImage, compressedImage, WRONG_QUALITY_MIN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void qualityCannotBeMoreThanOne()
    {
        SimpleImageReducer imageReducer = new SimpleImageReducer();
        File originalImage = mock(File.class);
        File compressedImage = mock(File.class);
        imageReducer.reduce(originalImage, compressedImage, WRONG_QUALITY_MAX);
    }
}
