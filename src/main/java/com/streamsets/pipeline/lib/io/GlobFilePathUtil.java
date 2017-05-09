package com.streamsets.pipeline.lib.io;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dongbin on 5/8/17.
 */
public class GlobFilePathUtil {

    private GlobFilePathUtil() {}

    public static Path getSubPath(Path path, int from, int to, boolean isPivot) {
        Path subPath = null;
        if (to - from > 0) {
            String baseName = path.getName(from).toString();
            baseName = (from == 0 && isPivot) ? "/" + baseName : baseName;
            String[] extraNames = new String[to - from -1];
            for (int i = from + 1; i < to; i++) {
                extraNames[i - from - 1] = path.getName(i).toString();
            }
            subPath = Paths.get(baseName, extraNames);
        } else if (isPivot) {
            subPath = Paths.get("/");
        }
        return subPath;
    }

    public static Path getPivotPath(Path path) {
        int nameCount = path.getNameCount();
        int wildcardIdx = 0;
        for (; wildcardIdx < nameCount && !hasGlobWildcard(path.getName(wildcardIdx).toString()); wildcardIdx++);
        return getSubPath(path, 0, wildcardIdx, true);
    }

    public static Path getWildcardPath(Path path) {
        int nameCount = path.getNameCount();
        int wildcardIdx = 0;
        for (; wildcardIdx < nameCount && !hasGlobWildcard(path.getName(wildcardIdx).toString()); wildcardIdx++);
        return getSubPath(path, wildcardIdx, nameCount, false);
    }

    public static boolean hasGlobWildcard(String name) {
        boolean escaped = false;
        for (char c: name.toCharArray()) {
            if (c == '\\') {
                escaped = true;
            } else {
                if (!escaped) {
                    switch (c) {
                        case '*':
                        case '?':
                        case '{':
                        case '[':
                            return true;
                    }
                } else {
                    escaped = false;
                }
            }
        }
        return false;
    }
}
