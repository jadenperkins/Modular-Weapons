package com.jadencode.main.material;

import java.awt.*;

/**
 * Created by Jaden on 2/4/2015.
 */
public class MaterialResource extends MaterialAttribute {

    private final MaterialBase     materialBase;
    private final MaterialModifier materialModifier;

    public MaterialResource(MaterialLibrary lib, MaterialBase base, MaterialModifier mod, String name, Color color, float weight, float f, float level) {
        super(name, color, weight, f, level);
        this.materialBase = base;
        this.materialModifier = mod;
        if(lib.getResource(base, mod) != null) {
            throw new IllegalArgumentException(name + " is already in use!");
        }
        lib.registerResource(this);
    }
    public MaterialResource(MaterialLibrary lib, MaterialBase base, MaterialModifier mod, Color color) {
        this(lib, base, mod, (mod.getName() + " " + base.getName()).trim(), color, base.getWeight() * mod.getWeight(), base.getMultiplier() * mod.getMultiplier(), base.getLevel() * mod.getLevel());
    }
//    public static void init() {
//
//        for(MaterialBase base : MaterialBase.getWoods().values()) {
//            for(MaterialModifier mod : MaterialModifier.getModifiers().values()) {
//                color color = combineColors(base.getColor(), mod.getColor());
//                MaterialResource resource = new MaterialResource(base, mod, color);
//            }
//        }
//        System.out.println("Initialized " + resources.size() + " wood resources!");
//    }
    private static Color combineColors(Color c1, Color c2) {

        if(c2 == null) {
            return c1;
        }

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

//        int ret = (int)Math.ceil(Math.min(255, Math.pow((double)(c1+1)*(double)(c1+1)*(double)(c1+1)*(double)(c2+1), 1D/4D)));
        return ret;
    }
    private static final int COMPONENT_1_TIMES = 3;
    private static final int COMPONENT_2_TIMES = 1;
    private static final int COMPONENT_TOTAL = COMPONENT_1_TIMES + COMPONENT_2_TIMES;

    public MaterialBase getMaterialBase() {
        return this.materialBase;
    }

    public MaterialModifier getMaterialModifier() {
        return this.materialModifier;
    }
}