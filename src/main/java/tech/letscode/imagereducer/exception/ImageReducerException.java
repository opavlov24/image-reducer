package tech.letscode.imagereducer.exception;

import javax.annotation.Nonnull;

public class ImageReducerException extends RuntimeException
{
    private static final long serialVersionUID = -5154230166864416274L;

    public ImageReducerException(@Nonnull String message, @Nonnull Throwable cause)
    {
        super(message, cause);
    }
}
