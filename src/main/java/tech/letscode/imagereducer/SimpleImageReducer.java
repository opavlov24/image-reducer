package tech.letscode.imagereducer;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.apache.commons.io.IOUtils.closeQuietly;

class SimpleImageReducer implements ImageReducer
{
    @Override
    public void reduce(File inImage, File outImage, float quality)
    {
        ImageWriter imageWriter = null;
        try {
            imageWriter = getImageWriterFor(inImage);
            ImageWriteParam compressionOptions = setCompressionOptions(quality, imageWriter);
            compress(inImage, outImage, imageWriter, compressionOptions);
        } finally {
            closeImageWriter(imageWriter);
        }
    }

    private void compress(File inImage, File outImage, ImageWriter imageWriter, ImageWriteParam defaultWriteParam)
    {
        FileImageOutputStream imageOutputStream = null;
        try {
            imageOutputStream = new FileImageOutputStream(outImage);
            imageWriter.setOutput(imageOutputStream);
            BufferedImage originalImage = ImageIO.read(inImage);
            IIOImage destImage = new IIOImage(originalImage, null, null);
            imageWriter.write(null, destImage, defaultWriteParam);
        } catch (IOException e) {
            throw new ImageReducerException("IO exception has been occurred while creating FileImageOutputStream", e);
        } finally {
            closeQuietly(imageOutputStream);
        }
    }

    private ImageWriteParam setCompressionOptions(float quality, ImageWriter imageWriter)
    {
        ImageWriteParam defaultWriteParam = imageWriter.getDefaultWriteParam();
        defaultWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        defaultWriteParam.setCompressionQuality(quality);
        return defaultWriteParam;
    }

    private void closeImageWriter(ImageWriter imageWriter)
    {
        if (imageWriter != null) {
            imageWriter.dispose();
        }
    }

    private ImageWriter getImageWriterFor(File inImage)
    {
        String extension = FilenameUtils.getExtension(inImage.getName());
        Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName(extension);
        if (!imageWriters.hasNext()) {
            throw new RuntimeException("Image reader for " + extension + " has not been found");
        }
        return imageWriters.next();
    }
}
