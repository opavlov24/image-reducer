package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

public interface OutputFileResolver
{
    File resolveFileBasedOnOriginal(@Nonnull File originalFile);
}
