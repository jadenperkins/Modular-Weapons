package com.main.plugins.pipeline;

import java.util.List;

public class PipelineObjectMaterialModifier extends PipelineObject {
    private final String color;
    private final float weight;
    private final float level;
    private final float mod;
    private final List<String> materials;

    public PipelineObjectMaterialModifier(String name, String color, float weight, float level, float mod, List<String> materials) {
        super(name);
        this.color = color;
        this.weight = weight;
        this.level = level;
        this.mod = mod;
        this.materials = materials;
    }
    public String getColor() {
        return color;
    }
    public float getWeight() {
        return weight;
    }
    public float getLevel() {
        return level;
    }
    public float getMod() {
        return mod;
    }
    public List<String> getMaterials() {
        return materials;
    }
}
