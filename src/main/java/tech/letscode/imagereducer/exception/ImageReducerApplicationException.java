package tech.letscode.imagereducer.exception;

import javax.annotation.Nonnull;

public class ImageReducerApplicationException extends RuntimeException
{
    private static final long serialVersionUID = 1624283318663889084L;

    public ImageReducerApplicationException(@Nonnull String message, @Nonnull Throwable cause)
    {
        super(message, cause);
    }
}
