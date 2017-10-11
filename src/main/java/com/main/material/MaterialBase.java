package com.main.material;

import com.main.generate.QualityLevel;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialBase implements Material {
    private final String name;
    private final Color color;
    private final float multiplier;
    private final int level;
    private final float weight;
    private final MaterialType materialType;
    private final QualityLevel qualityLevel;

    public MaterialBase(String name, Color color, float weight, float multiplier, int level, QualityLevel quality, MaterialType type) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.multiplier = multiplier;
        this.level = level;
        this.qualityLevel = quality;
        this.materialType = type;
    }
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public float getMultiplier() {
        return this.multiplier;
    }
    @Override
    public int getLevel() {
        return this.level;
    }
    @Override
    public MaterialType getMaterialType() {
        return materialType;
    }
    @Override
    public QualityLevel getQualityLevel() {
        return this.qualityLevel;
    }
    @Override
    public float getWeight() {
        return this.weight;
    }
}