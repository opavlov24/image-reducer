package tech.letscode.imagereducer.implementation.scanner;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static tech.letscode.imagereducer.util.TestUtils.rootDirectory;

public class SimpleDirectoryScannerTest
{
    @Test
    public void ifExtensionsAreNotSetReturnsAllFilesInDirectory() throws Exception
    {
        SimpleDirectoryScanner scanner = new SimpleDirectoryScanner();
        List<File> foundFiles = new ArrayList<>();
        scanner.addDirectoryListener(foundFiles::add);
        scanner.scan(rootDirectory(), true);
        assertTrue(foundFiles.size() == 2);
    }

    @Test
    public void ifExtensionIsSetReturnsOnlyMatchedFiles()
    {
        SimpleDirectoryScanner scanner = new SimpleDirectoryScanner();
        List<File> foundFiles = new ArrayList<>();
        scanner.addDirectoryListener(foundFiles::add);
        scanner.scan(rootDirectory(), true, "jpeg");
        assertTrue(foundFiles.size() == 1);
        File foundFile = foundFiles.get(0);
        assertTrue(foundFile.getName().equalsIgnoreCase("notReducedImage.jpeg"));
    }
}
