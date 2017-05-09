package com.streamsets.pipeline.lib.parser.shaded.com.google.code.regexp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * Created by dongbin on 5/9/17.
 */
public class Pattern {



    protected Pattern(String regex, int flags) {

    }

    /**
     * Compiles the given regular expression into a pattern
     *
     * @param regex the expression to be compiled
     * @return the pattern
     */
    public static Pattern compile(String regex) {
        return new Pattern(regex, 0);
    }

    /**
     * Compiles the given regular expression into a pattern with the given flags
     *
     * @param regex the expression to be compiled
     * @param flags Match flags, a bit mask that may include:
     * <ul>
     *   <li>{@link java.util.regex.Pattern#CASE_INSENSITIVE}</li>
     *   <li>{@link java.util.regex.Pattern#MULTILINE}</li>
     *   <li>{@link java.util.regex.Pattern#DOTALL}</li>
     *   <li>{@link java.util.regex.Pattern#UNICODE_CASE}</li>
     *   <li>{@link java.util.regex.Pattern#CANON_EQ}</li>
     *   <li>{@link java.util.regex.Pattern#UNIX_LINES}</li>
     *   <li>{@link java.util.regex.Pattern#LITERAL}</li>
     *   <li>{@link java.util.regex.Pattern#COMMENTS}</li>
     * </ul>
     * @return the pattern
     */
    public static Pattern compile(String regex, int flags) {
        return new Pattern(regex, flags);
    }

    /**
     * Gets the group index of a named capture group
     *
     * @param groupName name of capture group
     * @return group index or -1 if not found
     */


}
