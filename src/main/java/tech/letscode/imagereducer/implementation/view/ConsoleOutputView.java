package tech.letscode.imagereducer.implementation.view;

import org.apache.commons.lang3.Validate;
import tech.letscode.imagereducer.OutputView;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.PrintStream;

public class ConsoleOutputView implements OutputView
{
    private final PrintStream OUT = System.out;

    private final PrintStream ERR = System.err;

    @Override
    public void outputFileCannotBeCreatedFor(@Nonnull File originalFile)
    {
        Validate.notNull(originalFile, "originalFile is required");
        ERR.println("Output file cannot be created for original file - " + originalFile.getName());
    }

    @Override
    public void originalImageCannotBeReduced(@Nonnull File originalFile)
    {
        Validate.notNull(originalFile, "originalFile is required");
        ERR.println(originalFile + " - cannot be reduced");
    }

    @Override
    public void unexpectedErrorHasBeenOccurred(@Nonnull Exception exception)
    {
        Validate.notNull(exception, "exception is required");
        ERR.println("Unexpected error has been occurred. Message: " + exception.getMessage());
    }

    @Override
    public void imageReduced(@Nonnull File originalFile)
    {
        Validate.notNull(originalFile, "originalFile is required");
        OUT.println(originalFile.getName() + " has been reduced");
    }
}
