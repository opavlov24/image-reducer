package tech.letscode.imagereducer;

import java.io.File;

public interface ImageReducer
{
    public void reduce(File inImage, File outImage, float quality);
}
