package tech.letscode.imagereducer.exception;

import java.io.IOException;

public class ImageReducerException extends RuntimeException
{
    public ImageReducerException(String message, IOException cause)
    {
        super(message, cause);
    }
}
