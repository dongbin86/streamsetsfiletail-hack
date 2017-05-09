package com.streamsets.pipeline.lib.io;

import com.streamsets.pipeline.api.impl.Utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Created by dongbin on 5/8/17.
 */
public class PeriodicFilesRollModeFactory implements RollModeFactory {

    @Override
    public String getTokenForPattern() {
        return "${PATTERN}";
    }

    @Override
    public RollMode get(String fileName, String periodicPattern) {
        fileName = Paths.get(fileName).getFileName().toString();
        int tokenStart = fileName.indexOf(getTokenForPattern());
        int tokenEnd = tokenStart + getTokenForPattern().length();
        String preToken = fileName.substring(0, tokenStart);
        String postToken = fileName.substring(tokenEnd);
        String name = Pattern.quote(preToken) + periodicPattern + Pattern.quote(postToken);
        return new PeriodicRollMode(name);
    }

    private static class PeriodicRollMode implements RollMode {
        private String regexPattern;
        private PathMatcher fileMatcher;

        public PeriodicRollMode(String filePattern) {
            this.regexPattern = "regex:" + Utils.checkNotNull(filePattern, "regexPattern");
            fileMatcher = FileSystems.getDefault().getPathMatcher(this.regexPattern);
        }

        @Override
        public boolean isFirstAcceptable(String firstFileName) {
            return firstFileName == null || firstFileName.isEmpty() || fileMatcher.matches(Paths.get(firstFileName));
        }

        @Override
        public String getLiveFileName() {
            return null;
        }

        @Override
        public boolean isCurrentAcceptable(String currentName) {
            return fileMatcher.matches(Paths.get(currentName));
        }

        private class FileFilter implements DirectoryStream.Filter<Path> {
            private final String currentFileName;

            public FileFilter(Path currentFile) {
                this.currentFileName = currentFile.getFileName().toString();
            }

            @Override
            public boolean accept(Path entry) throws IOException {
                boolean accept = false;
                if (fileMatcher.matches(entry.getFileName())) {
                    accept = entry.getFileName().toString().compareTo(currentFileName) > 0;
                }
                return accept;
            }
        }

        @Override
        public boolean isFileRolled(LiveFile currentFile) throws IOException {
            DirectoryStream.Filter<Path> filter = new FileFilter(currentFile.getPath());
            try (DirectoryStream<Path> matches = Files.newDirectoryStream(currentFile.getPath().getParent(), filter)) {
                return matches.iterator().hasNext();
            }
        }

        @Override
        public Comparator<Path> getComparator() {
            return new Comparator<Path>() {
                @Override
                public int compare(Path o1, Path o2) {
                    return o1.compareTo(o2);
                }
            };
        }

        @Override
        public String getPattern() {
            return regexPattern;
        }

        @Override
        public String toString() {
            return "PERIODIC_PATTERN";
        }
    }

}
