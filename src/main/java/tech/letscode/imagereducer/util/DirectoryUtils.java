package tech.letscode.imagereducer.util;

import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;
import java.io.File;

public final class DirectoryUtils
{
    public static String pathWithoutFilename(@Nonnull File file)
    {
        Validate.notNull(file, "file is required");
        String absolutePath = file.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
    }
}
