package com.main.material;

import com.main.generate.QualityLevel;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialModified implements Material {
    private final Material baseMaterial;
    private final QualityLevel quality;
    private final MaterialModifier materialModifier;

    public MaterialModified(Material parent, QualityLevel quality, MaterialModifier mod) {
        this.baseMaterial = parent;
        this.quality = quality;
        this.materialModifier = mod;
    }
    public Material getBaseMaterial() {
        return baseMaterial;
    }
    public MaterialModifier getMaterialModifier() {
        return materialModifier;
    }

    @Override
    public Color getColor() {
        return combineColors(getBaseMaterial().getColor(), getMaterialModifier().getColor());
    }
    @Override
    public String getName() {
        return (getMaterialModifier().getName() + " " + getBaseMaterial().getName()).trim();
    }
    @Override
    public float getMultiplier() {
        return getBaseMaterial().getMultiplier() * getMaterialModifier().getMod();
    }
    @Override
    public int getLevel() {
        return (int) (getBaseMaterial().getLevel() * getMaterialModifier().getLevel());
    }
    @Override
    public MaterialType getMaterialType() {
        return getBaseMaterial().getMaterialType();
    }
    @Override
    public QualityLevel getQualityLevel() {
        return quality;
    }
    @Override
    public float getWeight() {
        return getBaseMaterial().getWeight() * getMaterialModifier().getWeight();
    }

    private static Color combineColors(Color c1, Color c2) {
        int newR = combine(c1.getRed(), c2.getRed());
        int newG = combine(c1.getGreen(), c2.getGreen());
        int newB = combine(c1.getBlue(), c2.getBlue());
        return new Color(newR, newG, newB);
    }
    private static int combine(int c1, int c2) {
        int mod = COMPONENT_1_TIMES * c1 + COMPONENT_2_TIMES * c2;
        int ret = (int)Math.ceil(Math.min(255, (double)mod / (double)(COMPONENT_TOTAL)));
        return ret;
    }
    private static final int COMPONENT_1_TIMES = 3;
    private static final int COMPONENT_2_TIMES = 1;
    private static final int COMPONENT_TOTAL = COMPONENT_1_TIMES + COMPONENT_2_TIMES;
}