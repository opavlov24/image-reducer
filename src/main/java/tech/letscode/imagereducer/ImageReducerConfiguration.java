package tech.letscode.imagereducer;

import lombok.Setter;

import javax.annotation.Nonnull;

@Setter
public class ImageReducerConfiguration
{
    public static final float DEFAULT_QUALITY = 0.5f;

    private String rootDirectory;

    private boolean recursively;

    private float quality = DEFAULT_QUALITY;

    private String[] extensions;

    private boolean suppressException;

    public ImageReducerConfiguration(@Nonnull String rootDirectory)
    {
        super();
        this.rootDirectory = rootDirectory;
    }

    @Nonnull
    public String rootDirectory()
    {
        return this.rootDirectory;
    }

    public boolean recursively()
    {
        return this.recursively;
    }

    public float quality()
    {
        return this.quality;
    }

    public String[] extensions()
    {
        return this.extensions;
    }

    public boolean suppressException()
    {
        return this.suppressException;
    }
}
