package tech.letscode.imagereducer.implementation.scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Validate;
import tech.letscode.imagereducer.DirectoryListener;
import tech.letscode.imagereducer.DirectoryScanner;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class SimpleDirectoryScanner implements DirectoryScanner
{
    private Set<DirectoryListener> listeners;

    @Override
    public void scan(@Nonnull String rootDirectoryPath, boolean recursively, String... extensions)
    {
        Validate.notNull(rootDirectoryPath, "rootDirectoryPath is required");
        File rootDirectory = new File(rootDirectoryPath);
        if (rootDirectory.exists() && rootDirectory.isDirectory())
        {
            extensions = extensions.length == 0 ? null : extensions;
            FileUtils.listFiles(rootDirectory, extensions, recursively).forEach(this::notifyListeners);
        }
    }

    private void notifyListeners(File file)
    {
        if (this.listeners != null)
        {
            this.listeners.forEach(listener -> listener.acceptFile(file));
        }
    }

    @Override
    public void addDirectoryListener(@Nonnull DirectoryListener listener)
    {
        Validate.notNull(listener, "listener is required");
        if (this.listeners == null)
        {
            this.listeners = new HashSet<>();
        }
        this.listeners.add(listener);
    }

}
