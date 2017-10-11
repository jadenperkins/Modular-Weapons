package com.main.pipeline;

public class PipelineObjectIcon extends PipelineObject {
    private final String base64;

    public PipelineObjectIcon(String name, String base64) {
        super(name);
        this.base64 = base64;
    }
    public String getBase64() {
        return base64;
    }
}
