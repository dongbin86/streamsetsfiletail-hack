package com.streamsets.pipeline.lib.io;

import com.streamsets.pipeline.api.impl.Utils;

/**
 * Created by dongbin on 5/8/17.
 */
public class FileEvent {

    public enum Action { START, END, ERROR}

    private final LiveFile file;
    private final Action action;

    FileEvent(LiveFile file, Action action) {
        this.file = Utils.checkNotNull(file, "file");
        this.action = Utils.checkNotNull(action, "action");
    }

    /**
     * Indicates if its a file start reading event.
     *
     * @return <code>true</code> if starting the file, <code>false</code> otherwise.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Returns the <code>LiveFile</code> triggering the event.
     *
     * @return the <code>LiveFile</code> triggering the event.
     */
    public LiveFile getFile() {
        return file;
    }

    public String toString() {
        return Utils.format("MultiFileReader.Event[file='{}' action='{}'", file, action);
    }

    @Override
    public int hashCode() {
        return file.hashCode() + action.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof FileEvent) {
            FileEvent other = (FileEvent) obj;
            return file.equals(other.file) && action == other.action;
        } else {
            return false;
        }
    }
}
