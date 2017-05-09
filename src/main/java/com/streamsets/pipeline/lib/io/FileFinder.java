package com.streamsets.pipeline.lib.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

/**
 * Created by dongbin on 5/8/17.
 */
public abstract class FileFinder {

    public abstract Set<Path> find() throws IOException;

    public abstract boolean forget(Path path);

    public abstract void close();
}
