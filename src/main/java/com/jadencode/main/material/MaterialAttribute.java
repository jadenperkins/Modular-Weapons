package com.jadencode.main.material;

import java.awt.*;

/**
 * Created by Jaden on 2/10/2015.
 */
public class MaterialAttribute {
    private final String name;
    private final Color  color;
    private final float  weight;
    private final float  multiplier;
    private final float  level;

    public MaterialAttribute(String name, Color color, float weight, float mod, float level) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.multiplier = mod;
        this.level = level;
    }
    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public float getWeight() {
        return this.weight;
    }

    public float getMultiplier() {
        return this.multiplier;
    }
    public float getLevel() {
        return this.level;
    }
}