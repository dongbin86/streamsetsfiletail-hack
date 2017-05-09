package com.streamsets.pipeline.lib.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by dongbin on 5/8/17.
 */
public interface LiveFileReader extends Closeable {
    LiveFile getLiveFile();


    Charset getCharset();


    long getOffset();


    boolean hasNext() throws IOException;


    LiveFileChunk next(long waitMillis) throws IOException;


    @Override
    void close() throws IOException;
}
