package tech.letscode.imagereducer;

import java.io.IOException;

public class ImageReducerException extends RuntimeException
{
    public ImageReducerException(String message, IOException cause)
    {
        super(message, cause);
    }
}
