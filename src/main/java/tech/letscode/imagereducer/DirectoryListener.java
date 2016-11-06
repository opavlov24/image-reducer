package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

public interface DirectoryListener
{
    public void acceptFile(@Nonnull File file);
}
