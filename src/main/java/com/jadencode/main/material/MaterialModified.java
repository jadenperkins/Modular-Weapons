package com.jadencode.main.material;

import com.jadencode.main.generate.QualityLevel;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialModified extends Material {

    public MaterialModified(String name, Color color, float weight, float multiplier, int level, QualityLevel quality, MaterialType type) {
        super(name, color, weight, multiplier, level, quality, type);
    }
    public MaterialModified(Material parent, QualityLevel quality, MaterialModifier mod) {
        this((mod.getName() + " " + parent.getName()).trim(), combineColors(parent.getColor(), mod.getColor()), parent.getWeight() * mod.getWeight(), parent.getMultiplier() * mod.getMod(), (int)(parent.getLevel() * mod.getLevel()), quality, parent.getMaterialType());
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