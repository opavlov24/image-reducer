package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

public interface ImageReducer
{
    void reduce(@Nonnull File inImage, @Nonnull File outImage, float quality);
}
