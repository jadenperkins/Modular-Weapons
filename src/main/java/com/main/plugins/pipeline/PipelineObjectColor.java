package com.main.plugins.pipeline;

import java.awt.*;

public class PipelineObjectColor extends PipelineObject {
    private final int red;
    private final int green;
    private final int blue;

    public PipelineObjectColor(String name, Color color) {
        super(name);
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }
    private int getRed() {
        return red;
    }
    private int getGreen() {
        return green;
    }
    private int getBlue() {
        return blue;
    }
    public Color makeColor() {
        return new Color(getRed(), getGreen(), getBlue());
    }
}
