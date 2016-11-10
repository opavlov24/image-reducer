package tech.letscode.imagereducer;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CommandLineConfigurationTest
{
    @Test
    public void requiredArgsMustBeSet()
    {
        String[] args = new String[]{"image-reducer", "-d", "/home/user", "-e", "jpeg"};
        ImageReducerConfiguration configuration = CommandLineConfiguration.build(args);
        assertNotNull(configuration);
    }

    @Test
    public void ifDirectoryIsNotSetReturnNull()
    {
        String[] args = new String[]{"image-reducer", "-e", "jpeg"};
        ImageReducerConfiguration configuration = CommandLineConfiguration.build(args);
        assertNull(configuration);
    }

    @Test
    public void ifExtensionsAreNotSetReturnNull()
    {
        String[] args = new String[]{"image-reducer", "-d", "/home/user"};
        ImageReducerConfiguration configuration = CommandLineConfiguration.build(args);
        assertNull(configuration);
    }
}
