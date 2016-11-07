package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

public class StubDirectoryScanner implements DirectoryScanner
{
    private final File file;
    private DirectoryListener listener;

    public StubDirectoryScanner(File file)
    {
        super();
        this.file = file;
    }

    @Override
    public void scan(@Nonnull String rootDirectory, boolean recursively, String... extensions)
    {
        listener.acceptFile(this.file);
    }

    @Override
    public void addDirectoryListener(@Nonnull DirectoryListener listener)
    {
        this.listener = listener;
    }
}
