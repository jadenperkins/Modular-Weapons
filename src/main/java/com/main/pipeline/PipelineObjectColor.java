package com.main.pipeline;

public class PipelineObjectColor extends PipelineObject {
    private final int r;
    private final int g;
    private final int b;

    public PipelineObjectColor(String name, int r, int g, int b) {
        super(name);
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public int getRed() {
        return r;
    }
    public int getGreen() {
        return g;
    }
    public int getBlue() {
        return b;
    }
}
