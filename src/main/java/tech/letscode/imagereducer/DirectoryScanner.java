package tech.letscode.imagereducer;

import javax.annotation.Nonnull;

public interface DirectoryScanner
{
    void scan(@Nonnull String rootDirectory, boolean recursively, String... extensions);

    void addDirectoryListener(@Nonnull DirectoryListener listener);

}
