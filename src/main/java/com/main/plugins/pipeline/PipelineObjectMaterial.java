package com.main.plugins.pipeline;

public class PipelineObjectMaterial extends PipelineObject {
    private final String color;
    private final float weight;
    private final float mod;
    private final int level;
    private final String material;

    public PipelineObjectMaterial(String name, String color, float weight, float mod, int level, String material) {
        super(name);
        this.color = color;
        this.weight = weight;
        this.mod = mod;
        this.level = level;
        this.material = material;
    }
    public String getColor() {
        return color;
    }
    public float getWeight() {
        return weight;
    }
    public float getMod() {
        return mod;
    }
    public int getLevel() {
        return level;
    }
    public String getMaterial() {
        return material;
    }
}
