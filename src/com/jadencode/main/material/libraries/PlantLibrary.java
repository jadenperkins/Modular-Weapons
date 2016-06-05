package com.upadvisor.main.material.libraries;

import com.upadvisor.main.material.MaterialBase;
import com.upadvisor.main.material.MaterialLibrary;
import com.upadvisor.main.material.MaterialModifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 2/11/2015.
 */
public class PlantLibrary extends MaterialLibrary {

    private static final PlantLibrary instance = new PlantLibrary();

    public MaterialBase cotton;
    public MaterialBase jute;

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
        super("Fabric");
    }

    @Override
    public void loadMaterials() {

//        this.cotton = new MaterialBase(MaterialLibrary.getPlantLibrary(), "Cotton", Colors.PLANT_COTTON, 1F, 1);
//        this.jute   = new MaterialBase(MaterialLibrary.getPlantLibrary(), "Jute", Colors.PLANT_JUTE, 1F, 25);
//
//        this.none     = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "", null, 27F).setLevelMod(1F);
//        this.hardy    = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Hardy", Colors.PLANT_MOD_HARDY, 1F).setLevelMod(4F);
//        this.young    = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Young", Colors.PLANT_MOD_YOUNG, 1F).setLevelMod(0.5F);
//        this.withered = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Withered", Colors.PLANT_MOD_WITHERED, 1F).setLevelMod(0.3F);
//        this.creeping = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Creeping", Colors.PLANT_MOD_CREEPING, 1F).setLevelMod(2.5F);
//        this.wild     = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Wild", Colors.PLANT_MOD_WILD, 1F).setLevelMod(3F);
//        this.mature   = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Mature", Colors.PLANT_MOD_MATURE, 1F).setLevelMod(3.5F);
//        this.extinct  = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Extinct", Colors.PLANT_MOD_EXTINCT, 1F).setLevelMod(2F);
//        this.juvenile = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Juvenile", Colors.PLANT_MOD_JUVENILE, 1F).setLevelMod(0.8F);
//        this.leafy    = new MaterialModifier(MaterialLibrary.getPlantLibrary(), "Leafy", Colors.PLANT_MOD_LEAFY, 1F).setLevelMod(3.8F);


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