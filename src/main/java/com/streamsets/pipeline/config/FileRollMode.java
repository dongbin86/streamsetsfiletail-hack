package com.streamsets.pipeline.config;

import com.streamsets.pipeline.lib.io.LogRollModeFactory;
import com.streamsets.pipeline.lib.io.PeriodicFilesRollModeFactory;
import com.streamsets.pipeline.lib.io.RollMode;
import com.streamsets.pipeline.lib.io.RollModeFactory;

/**
 * Created by dongbin on 5/8/17.
 */
public enum FileRollMode {
    REVERSE_COUNTER("Active File with Reverse Counter Files", LogRollModeFactory.REVERSE_COUNTER),
    DATE_YYYY_MM("Active File with .yyyy-MM Files", LogRollModeFactory.DATE_YYYY_MM),
    DATE_YYYY_MM_DD("Active File with .yyyy-MM-dd Files", LogRollModeFactory.DATE_YYYY_MM_DD),
    DATE_YYYY_MM_DD_HH("Active File with .yyyy-MM-dd-HH Files", LogRollModeFactory.DATE_YYYY_MM_DD_HH),
    DATE_YYYY_MM_DD_HH_MM("Active File with .yyyy-MM-dd-HH-mm Files", LogRollModeFactory.DATE_YYYY_MM_DD_HH_MM),
    DATE_YYYY_WW("Active File with .yyyy-ww Files", LogRollModeFactory.DATE_YYYY_WW),
    ALPHABETICAL("Active File with Alphabetical Files", LogRollModeFactory.ALPHABETICAL),
    PATTERN("Files matching a pattern", new PeriodicFilesRollModeFactory()),;

    private final String label;
    private final RollModeFactory factory;

    FileRollMode(String label, RollModeFactory factory) {
        this.label = label;
        this.factory = factory;
    }

    public String getLabel() {
        return label;
    }

    public RollMode createRollMode(String fileName, String pattern) {
        return factory.get(fileName, pattern);
    }

    public String getTokenForPattern() {
        return factory.getTokenForPattern();
    }

}
