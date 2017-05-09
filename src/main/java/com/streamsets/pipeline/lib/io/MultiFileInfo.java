package com.streamsets.pipeline.lib.io;

import com.streamsets.pipeline.config.FileRollMode;
/**
 * Created by dongbin on 5/8/17.
 */
public class MultiFileInfo {
    private final String tag;
    private final String fileFullPath;
    private final FileRollMode fileRollMode;
    private final String pattern;
    private final String firstFile;
    private final MultiFileInfo source;
    private final String multiLineMainLinePatter;

    /**
     * Creates a <code>FileInfo</code>
     *
     * @param tag          file tag.
     * @param fileFullPath file full path.
     * @param fileRollMode file roll mode.
     * @param pattern      file pattern, if any.
     * @param firstFile    first file to read.
     */
    public MultiFileInfo(String tag, String fileFullPath, FileRollMode fileRollMode, String pattern, String firstFile,
                         String multiLineMainLinePatter) {
        this.tag = tag;
        this.fileFullPath = fileFullPath;
        this.fileRollMode = fileRollMode;
        this.pattern = pattern;
        this.firstFile = firstFile;
        this.multiLineMainLinePatter = multiLineMainLinePatter;
        source = null;
    }

    public MultiFileInfo(MultiFileInfo source, String resolvedPath) {
        this.tag = source.getTag();
        this.fileFullPath = resolvedPath;
        this.fileRollMode = source.getFileRollMode();
        this.pattern = source.getPattern();
        this.firstFile = null;
        this.multiLineMainLinePatter = source.getMultiLineMainLinePatter();
        this.source = source;
    }

    public MultiFileInfo getSource() {
        return source;
    }

    public String getFileKey() {
        return getFileFullPath() + "||" + getPattern();
    }

    public String getTag() {
        return tag;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public FileRollMode getFileRollMode() {
        return fileRollMode;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFirstFile() {
        return firstFile;
    }

    public String getMultiLineMainLinePatter() {
        return multiLineMainLinePatter;
    }


}
