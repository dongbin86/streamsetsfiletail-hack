package com.streamsets.pipeline.lib.io;

import com.streamsets.pipeline.lib.parser.shaded.com.google.code.regexp.Pattern;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by dongbin on 5/9/17.
 */
public class MultiLineLiveFileReader implements LiveFileReader{

    public MultiLineLiveFileReader(String tag, LiveFileReader reader, Pattern mainLinePattern) {

    }

    @Override
    public LiveFile getLiveFile() {
        return null;
    }

    @Override
    public Charset getCharset() {
        return null;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public boolean hasNext() throws IOException {
        return false;
    }

    @Override
    public LiveFileChunk next(long waitMillis) throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
