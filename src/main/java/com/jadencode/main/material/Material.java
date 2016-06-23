package com.jadencode.main.material;

import com.jadencode.main.constants.Materials;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.util.WeightedItem;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Jaden on 2/4/2015.
 */
public class Material implements WeightedItem, QualityObject {
    private final String name;
    private final Color color;
    private final float multiplier;
    private final int level;
    private final float weight;
    private final MaterialType materialType;
    private final QualityLevel qualityLevel;

    public Material(String name, Color color, float weight, float multiplier, int level, QualityLevel quality, MaterialType type) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.multiplier = multiplier;
        this.level = level;
        this.qualityLevel = quality;
        this.materialType = type;
    }

    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public float getMultiplier() {
        return this.multiplier;
    }
    public int getLevel() {
        return this.level;
    }
    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public QualityLevel getQualityLevel() {
        return this.qualityLevel;
    }
    @Override
    public List<QualityObject> getQualityObjects() {
        return Arrays.asList(this);
    }
}