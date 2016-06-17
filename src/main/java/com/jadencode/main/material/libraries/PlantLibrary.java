package com.jadencode.main.material.libraries;

import com.jadencode.main.constants.Colors;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.material.MaterialModifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 2/11/2015.
 */
public class PlantLibrary extends MaterialType {

    private static final PlantLibrary instance = new PlantLibrary();

    public Material cotton;
    public Material jute;

    public MaterialModifier none;
    public MaterialModifier hardy;
    public MaterialModifier young;
    public MaterialModifier withered;
    public MaterialModifier creeping;
    public MaterialModifier wild;
    public MaterialModifier mature;
    public MaterialModifier extinct;
    public MaterialModifier juvenile;
    public MaterialModifier leafy;

    private static final List<String> syllablesFirst  = Arrays.asList("Snake", "Blood", "Gleam", "String", "Witch's ");
    private static final List<String> syllablesSecond = Arrays.asList("thorn", "root", "leaf", "stem", "blade", "grass",
            "bell", "flower", "bloom", "blossom");

    public static PlantLibrary getInstance() {
        return instance;
    }

    public PlantLibrary() {
        super("Fabric");//, null);
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
////                int r = 110 + ((int)Math.abs(code % s1.hashCode()) & 255);
////                int g = 60 + ((int)Math.abs(code % s2.hashCode()) & 150);
////                int b = 0;
//
//                int r = 60 + ((int)Math.abs(code & s1.hashCode())) & 255;
//                int g = 140 + ((int)Math.abs(code & s2.hashCode())) & 255;
//                int b = 100 + ((int)Math.abs(code & name.hashCode())) & 255;
//
//                //12541 % 40 + 21
//                int level = (int)Math.abs(code % 41) + 20;
//
//                Color c = new Color(r, g, b);
//                float weight = (float)(hash % 5 + 1) / 5F;
//
//                float str = (float) (code % 20) / 10F;
//                float rng = (float) ((code + 7) % 20) / 10F;
//                float wkn = (float) Math.pow(str * rng, -1F);
//
//                MaterialBase base = new MaterialBase(MaterialLibrary.getPlantLibrary(), name, c, weight, str, rng, wkn, level);
//            }
//        }
    }
}