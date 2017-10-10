package com.main.material;

import com.main.util.WeightedItem;

import java.awt.*;
import java.util.List;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialModifier implements WeightedItem {
    private final String name;
    private final Color color;
    private final float mod;
    private final float level;
    private final float weight;

    public MaterialModifier(String name, Color color, float mod, float level, float weight, List<MaterialType> types) {
        this.name = name;
        this.color = color;
        this.mod = mod;
        this.level = level;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    public float getMod() {
        return mod;
    }
    public float getLevel() {
        return level;
    }
    @Override
    public float getWeight() {
        return weight;
    }
}