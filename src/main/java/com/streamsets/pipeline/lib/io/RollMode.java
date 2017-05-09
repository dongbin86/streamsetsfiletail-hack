package com.streamsets.pipeline.lib.io;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * Created by dongbin on 5/8/17.
 */
public interface RollMode {

    public String getLiveFileName();

    //must be a PathMatcher glob: or regex: pattern
    public String getPattern();

    public boolean isFirstAcceptable(String firstFileName);

    public boolean isCurrentAcceptable(String currentName);

    public boolean isFileRolled(LiveFile currentFile) throws IOException;

    public Comparator<Path> getComparator();
}
