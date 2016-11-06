package tech.letscode.imagereducer;

import java.io.File;

public interface OutputFileResolver
{
    public File resolveFileBasedOnOriginal(File originalFile);
}
