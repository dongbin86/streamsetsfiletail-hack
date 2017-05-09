package com.streamsets.pipeline.lib.util;

/**
 * Created by dongbin on 5/8/17.
 */
public class ThreadUtil {

    private ThreadUtil() {}

    public static boolean sleep(long milliseconds) {
        //checking if we got pre-interrupted.
        boolean interrupted = Thread.interrupted();
        if (!interrupted) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException ex) {
                interrupted = true;
                // clearing the interrupt flag
                Thread.interrupted();
            }
        }
        return !interrupted;
    }
}
