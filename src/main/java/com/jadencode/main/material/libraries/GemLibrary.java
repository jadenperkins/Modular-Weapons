package com.jadencode.main.material.libraries;

import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.material.MaterialModifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 6/8/2015.
 */
public class GemLibrary extends MaterialType {

    private static final GemLibrary instance = new GemLibrary();

    public Material jade;
    public Material topaz;
    public Material garnet;
    public Material amethyst;
    public Material sapphire;
    public Material emerald;
    public Material ruby;
    public Material diamond;

    public MaterialModifier none;
    public MaterialModifier dark;
    public MaterialModifier rough;
    public MaterialModifier vibrant;
    public MaterialModifier faded;
    public MaterialModifier dull;

    private static final List<String> syllablesFirst  = Arrays.asList("Blood", "Dragon", "Shadow", "Sky", "Thunder");
    private static final List<String> syllablesSecond = Arrays.asList("stone", "rock", "gem", "spar", "quartz", "glass", "jewel");

    private GemLibrary() {
        super("Gem");//, null);
    }

    public static GemLibrary getInstance() {
        return instance;
    }

//    @Override
    public void loadMaterials() {

//        for (String s1 : syllablesFirst) {
//            for (String s2 : syllablesSecond) {
//                String name = s1 + s2;
//                long hash = name.hashCode();
//                long seed = Main.theWorld.getSeed();
//                long code = hash % seed;
//
//                int r = 80 + ((int)Math.abs(code & s1.hashCode())) & 255;
//                int g = 80 + ((int)Math.abs(code & s2.hashCode())) & 255;
//                int b = 80 + ((int)Math.abs(code & name.hashCode())) & 255;
//
//                int level = (int)Math.abs(code % 41) + 20;
//
//                Color c = new Color(r, g, b);
//                float weight = (float)(hash % 5 + 1) / 5F;
//
//                float str = (float) (code % 20) / 10F;
//                float rng = (float) ((code + 7) % 20) / 10F;
//                float wkn = (float) Math.pow(str * rng, -1F);
//
//                MaterialBase base = new MaterialBase(MaterialLibrary.getGemLibrary(), name, c, weight, str, rng, wkn, level);
//            }
//        }
    }
}