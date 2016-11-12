package tech.letscode.imagereducer.exception;

public class ImageReducerApplicationException extends RuntimeException
{
    private static final long serialVersionUID = 1624283318663889084L;

    public ImageReducerApplicationException(String message, Exception cause)
    {
        super(message, cause);
    }
}
