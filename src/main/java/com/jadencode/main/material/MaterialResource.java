package com.jadencode.main.material;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialResource extends Material {

    public MaterialResource(String name, Color color, float weight, float multiplier, int level, MaterialType type) {
        super(name, color, weight, multiplier, level, type);
    }
    public MaterialResource(Material parent, MaterialModifier mod) {
        this((mod.getName() + " " + parent.getName()).trim(), combineColors(parent.getColor(), mod.getColor()), parent.getWeight() * mod.getWeight(), parent.getMultiplier() * mod.getMod(), (int)(parent.getLevel() * mod.getLevel()), parent.getMaterialType());
    }
    private static Color combineColors(Color c1, Color c2) {
        int r1 = c1.getRed();
        int g1 = c1.getGreen();
        int b1 = c1.getBlue();

        int r2 = c2.getRed();
        int g2 = c2.getGreen();
        int b2 = c2.getBlue();

        int newR = combine(r1, r2);
        int newG = combine(g1, g2);
        int newB = combine(b1, b2);

        return new Color(newR, newG, newB);
    }
    private static int combine(int c1, int c2) {
        int mod = 0;

        for(int i = 0; i < COMPONENT_1_TIMES; i++) {
            mod += c1;
        }
        for(int i = 0; i < COMPONENT_2_TIMES; i++) {
            mod += c2;
        }
        int ret = (int)Math.ceil(Math.min(255, (double)mod / (double)(COMPONENT_TOTAL)));

        return ret;
    }
    private static final int COMPONENT_1_TIMES = 3;
    private static final int COMPONENT_2_TIMES = 1;
    private static final int COMPONENT_TOTAL = COMPONENT_1_TIMES + COMPONENT_2_TIMES;
}