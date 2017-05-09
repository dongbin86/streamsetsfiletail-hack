package com.streamsets.pipeline.lib.io;

/**
 * Created by dongbin on 5/9/17.
 */
public interface FileEventPublisher {

    public void publish(FileEvent event);
}
