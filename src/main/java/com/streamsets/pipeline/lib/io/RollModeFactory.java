package com.streamsets.pipeline.lib.io;

/**
 * Created by dongbin on 5/8/17.
 */
public interface RollModeFactory {
    public String getTokenForPattern();

    public RollMode get(String fileName, String periodicPattern);
}
