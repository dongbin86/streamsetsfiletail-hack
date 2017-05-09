package com.streamsets.pipeline.lib.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dongbin on 5/8/17.
 */
public class DirectoryPathCreationWatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryPathCreationWatcher.class);

    private final Collection<Path> pathsWatched = Collections.synchronizedCollection(new TreeSet<Path>());
    private final ConcurrentLinkedQueue<Path> foundPathsQueue = new ConcurrentLinkedQueue<Path>();
    private final int scanIntervalSecs;
    private final Runnable finder;

    private ScheduledExecutorService executor = null;

    public DirectoryPathCreationWatcher(Collection<Path> paths, int scanIntervalSecs){
        this.scanIntervalSecs = scanIntervalSecs;
        pathsWatched.addAll(paths);
        finder = new Runnable() {
            @Override
            public void run() {
                synchronized (pathsWatched) {
                    Iterator<Path> dirPathIterator = pathsWatched.iterator();
                    while (dirPathIterator.hasNext()) {
                        Path dirPath = dirPathIterator.next();
                        if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
                            foundPathsQueue.offer(dirPath);
                            dirPathIterator.remove();
                            LOGGER.debug("Found Path : {}", dirPath.toAbsolutePath().toString());
                        }
                    }
                }
            }
        };

        //Start finding the directory in the background.
        if (scanIntervalSecs > 0) {
            executor = new SafeScheduledExecutorService(1, "Directory Creation Watcher");
            executor.scheduleWithFixedDelay(finder, 0, scanIntervalSecs, TimeUnit.SECONDS);
        }
    }

    public Set<Path> find() {
        //During Synchronous run.
        if (scanIntervalSecs == 0) {
            finder.run();
        }

        Set<Path> foundPaths = new HashSet<Path>();
        Path detectedPath;
        while ( (detectedPath = foundPathsQueue.poll()) != null) {
            foundPaths.add(detectedPath);
        }

        if (pathsWatched.isEmpty()) {
            LOGGER.debug("All Directory Paths are found");
            close();
        }
        return foundPaths;
    }

    public void close() {
        if (executor != null) {
            LOGGER.debug("Directory Creation Watcher - Closing");
            executor.shutdownNow();
            executor = null;
        }
    }
}
