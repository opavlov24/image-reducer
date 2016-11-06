package tech.letscode.imagereducer;

import org.apache.commons.io.FileUtils;

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
        assert rootDirectoryPath != null;
        File rootDirectory = new File(rootDirectoryPath);
        if (rootDirectory.exists() && rootDirectory.isDirectory()) {
            extensions = extensions.length == 0 ? null : extensions;
            FileUtils.listFiles(rootDirectory, extensions, recursively).forEach(this::notifyListeners);
        }
    }

    private void notifyListeners(File file)
    {
        if (this.listeners != null) {
            this.listeners.forEach(listener -> listener.acceptFile(file));
        }
    }

    @Override
    public void addDirectoryListener(@Nonnull DirectoryListener listener)
    {
        assert listener != null;
        if (this.listeners == null) {
            this.listeners = new HashSet<>();
        }
        this.listeners.add(listener);
    }

}
