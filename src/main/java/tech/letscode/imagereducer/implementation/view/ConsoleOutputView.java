package tech.letscode.imagereducer.implementation.view;

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
        ERR.println("Output file cannot be created for original file - " + originalFile.getName());
    }

    @Override
    public void originalImageCannotBeReduced(@Nonnull File originalFile)
    {
        ERR.println(originalFile + " - cannot be reduced");
    }

    @Override
    public void unexpectedErrorHasBeenOccurred(@Nonnull Exception exception)
    {
        ERR.println("Unexpected error has been occurred. Message: " + exception.getMessage());
    }

    @Override
    public void imageReduced(@Nonnull File originalFile)
    {
        OUT.println(originalFile.getName() + " has been reduced");
    }
}
