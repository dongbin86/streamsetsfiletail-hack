package com.streamsets.pipeline.lib.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dongbin on 5/8/17.
 */
public interface FileContextProvider extends Closeable {
    void setOffsets(Map<String, String> offsets) throws IOException;

    FileContext next();

    boolean didFullLoop();

    void startNewLoop();

    Map<String, String> getOffsets() throws IOException;

    Map<String, Long> getOffsetsLag(Map<String, String> offsetMap) throws IOException;

    Map<String, Long> getPendingFiles() throws IOException;

    void purge();
}
