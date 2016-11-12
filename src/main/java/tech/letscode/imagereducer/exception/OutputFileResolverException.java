package tech.letscode.imagereducer.exception;

import javax.annotation.Nonnull;

public class OutputFileResolverException extends RuntimeException
{
    private static final long serialVersionUID = 4684298761821444653L;

    public OutputFileResolverException(@Nonnull String message, @Nonnull Throwable cause)
    {
        super(message, cause);
    }
}
