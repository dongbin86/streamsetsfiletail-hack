package com.streamsets.pipeline.config;

/**
 * Created by dongbin on 5/9/17.
 */
public enum PostProcessingOptions {

    NONE("None"),
    ARCHIVE("Archive"),
    DELETE("Delete"),
    ;


    private final String label;

    PostProcessingOptions(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
