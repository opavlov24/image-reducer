package tech.letscode.imagereducer;

import javax.annotation.Nonnull;

public interface DirectoryScanner
{
    public void scan(@Nonnull String rootDirectory, boolean recursively, String... extensions);

    public void addDirectoryListener(@Nonnull DirectoryListener listener);

}
