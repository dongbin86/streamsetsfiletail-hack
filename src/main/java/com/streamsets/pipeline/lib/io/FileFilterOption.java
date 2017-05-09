package com.streamsets.pipeline.lib.io;

import com.streamsets.pipeline.api.impl.Utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * Created by dongbin on 5/8/17.
 */
public enum  FileFilterOption {

    FILTER_FILES_ONLY,
    FILTER_DIRECTORIES_ONLY,
    FILTER_REGULAR_FILES_ONLY,
    FILTER_DIRECTORY_REGULAR_FILES,
    NO_FILTER_OPTION;

    public static DirectoryStream.Filter<Path> getFilter(
            final Set<Path> foundPaths,
            FileFilterOption option
    ) {
        switch (option) {
            case FILTER_FILES_ONLY:
                return new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return !foundPaths.contains(entry) && !Files.isDirectory(entry);
                    }
                };
            case FILTER_DIRECTORIES_ONLY:
                return new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return !foundPaths.contains(entry) && Files.isDirectory(entry);
                    }
                };
            case FILTER_REGULAR_FILES_ONLY:
                return new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return !foundPaths.contains(entry) && Files.isRegularFile(entry);
                    }
                };
            case FILTER_DIRECTORY_REGULAR_FILES:
                return new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return !foundPaths.contains(entry) && (Files.isDirectory(entry) || Files.isRegularFile(entry));
                    }
                };
            case NO_FILTER_OPTION:
                return new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return !foundPaths.contains(entry);
                    }
                };
            default:
                throw new IllegalArgumentException(Utils.format("Invalid File Filter option: {}", option.name()));
        }
    }
}
