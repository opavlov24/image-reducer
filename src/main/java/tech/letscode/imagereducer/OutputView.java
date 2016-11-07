package tech.letscode.imagereducer;

import javax.annotation.Nonnull;
import java.io.File;

public interface OutputView
{
    void outputFileCannotBeCreatedFor(@Nonnull File originalFile);

    void originalImageCannotBeReduced(@Nonnull File originalFile);

    void unexpectedErrorHasBeenOccurred(@Nonnull Exception exception);

    void imageReduced(@Nonnull File originalFile);
}
