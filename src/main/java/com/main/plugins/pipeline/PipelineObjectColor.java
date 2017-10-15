package com.main.plugins.pipeline;

public class PipelineObjectColor extends PipelineObject {
    private final int red;
    private final int green;
    private final int blue;

    public PipelineObjectColor(String name, int r, int g, int b) {
        super(name);
        this.red = r;
        this.green = g;
        this.blue = b;
    }
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
}
