package tech.letscode.imagereducer.util;

import javax.annotation.Nonnull;
import java.io.File;

public final class DirectoryUtils
{
    public static String pathWithoutFilename(@Nonnull File file)
    {
        String absolutePath = file.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
    }
}
