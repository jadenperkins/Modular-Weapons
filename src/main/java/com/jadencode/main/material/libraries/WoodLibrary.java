package com.jadencode.main.material.libraries;

import com.jadencode.main.constants.Colors;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.material.MaterialModifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 2/10/2015.
 */
public class WoodLibrary extends MaterialType {

    private static final WoodLibrary instance = new WoodLibrary();

    public Material ash;
    public Material beech;
    public Material birch;
    public Material elm;
    public Material ebony;
    public Material holly;
    public Material hornbeam;
    public Material juniper;
    public Material koa;
    public Material laurel;
    public Material locust;
    public Material maple;
    public Material mesquite;
    public Material mahogany;
    public Material oak;
    public Material palm;
    public Material snakewood;
    public Material teak;
    public Material walnut;
    public Material yew;
    public Material zebrawood;

    public MaterialModifier none;
    public MaterialModifier dark;
    public MaterialModifier black;
    public MaterialModifier red;
    public MaterialModifier ancient;
    public MaterialModifier light;
    public MaterialModifier white;
    public MaterialModifier hardy;
    public MaterialModifier polar;

    //x = 0.7(8)/(1-0.7)
    //x =
    private static final List<String> syllablesFirst  = Arrays.asList("Ac", "Za", "Mat", "Ni");
    private static final List<String> syllablesSecond = Arrays.asList("tan", "rop", "cat", "mat");
    private static final List<String> syllablesThird  = Arrays.asList("ta", "in", "or", "za");

    private WoodLibrary() {
        super("Wood", null);
    }

//    @Override
    public void loadMaterials() {



//        for (String s1 : syllablesFirst) {
//            for (String s2 : syllablesSecond) {
//                for (String s3 : syllablesThird) {
//                    String name = s1 + s2 + s3;
//                    long hash = name.hashCode();
//                    long seed = Main.theWorld.getSeed();
//                    long code = hash % seed;
//
//                    int r = 110 + ((int)Math.abs(code % s1.hashCode()) & 255) & 255;
//                    int g = 60 + ((int)Math.abs(code % s2.hashCode()) & 150) & 255;
//                    int b = 0;
//
//                    int level = (int)Math.abs((code % 41)) + 20;
//
//                    Color c = new Color(r, g, b);
//                    float weight = (float)(hash % 5 + 1) / 5F;
//
//                    float str = (float) (code % 20) / 10F;
//                    float rng = (float) ((code + 7) % 20) / 10F;
//                    float wkn = (float) Math.pow(str * rng, -1F);
//
//                    MaterialBase base = new MaterialBase(MaterialLibrary.getWoodLibrary(), name, c, weight, str, rng, wkn, level);
//                }
//            }
//        }
    }
    public static WoodLibrary getInstance() {
        return instance;
    }
}
