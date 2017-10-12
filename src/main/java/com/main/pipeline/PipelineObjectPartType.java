package com.main.pipeline;

public class PipelineObjectPartType extends PipelineObject {
    private final String icon;

    public PipelineObjectPartType(String name, String icon) {
        super(name);
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }
}
