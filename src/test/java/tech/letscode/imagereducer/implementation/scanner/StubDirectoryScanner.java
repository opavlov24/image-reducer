package tech.letscode.imagereducer.implementation.scanner;

import tech.letscode.imagereducer.DirectoryListener;
import tech.letscode.imagereducer.DirectoryScanner;
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
    public void scan(String rootDirectory, boolean recursively, String... extensions)
    {
        listener.acceptFile(this.file);
    }

    @Override
    public void addDirectoryListener(DirectoryListener listener)
    {
        this.listener = listener;
    }
}
