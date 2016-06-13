package com.jadencode.main.material.libraries;

import com.jadencode.main.material.MaterialType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 2/10/2015.
 */
public class MetalLibrary extends MaterialType {

    private static final MetalLibrary instance = new MetalLibrary();

    //    public static final MaterialBase bronze = new MaterialBase("Bronze", Colors.METAL_BRONZE, 50F).setBaseLevel(1);
    //    public static final MaterialBase steel  = new MaterialBase("Steel", Colors.METAL_STEEL, 30F).setBaseLevel(20);

    //w = p(t)/(1-p)
    //w = 27
    private static final List<String> syllablesFirst  = Arrays.asList("Meg", "Ex", "Ant", "Mas", "Ox", "Ax", "Bis");
    private static final List<String> syllablesSecond = Arrays.asList("or", "an", "net", "et", "en");
    private static final List<String> syllablesThird  = Arrays.asList("ite", "ium");

    private MetalLibrary() {
        super("Metal");
    }

    public void loadMaterials() {

//        this.iron = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Iron", Colors.METAL_IRON, 40F, 1F, 1F, 1F, 1);
//        this.silver = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Silver", Colors.METAL_SILVER, 20F, 0.6F, 1.0F, 0.6F, 10);
//        this.gold     = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Gold", Colors.METAL_GOLD, 15F, 0.8F, 1.25F, 1.0F, 20);
//        this.cobalt   = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Cobalt", Colors.METAL_COBALT, 5F, 1.0F, 0.8F, 0.8F, 30);
//        this.tungsten = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Tungsten", Colors.METAL_TUNGSTEN, 2.5F, 1.4F, 0.8F, 1.12F, 40);
//        this.titanium = new MaterialBase(MaterialLibrary.getMetalLibrary(), "Titanium", Colors.METAL_TITANIUM, 1.25F, 1.8F, 0.8F, 1.44F, 50);
//
//        this.none     = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "", null, 27F, 1F, 1F, 1F);
//        this.lunar    = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Lunar", Colors.METAL_MOD_LUNAR, 1F, 0.5F, 2.0F, 1.0F).setLevelMod(3.5F);
//        this.solar    = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Solar", Colors.METAL_MOD_SOLAR, 1F, 0.5F, 1.0F, 0.5F).setLevelMod(2.5F);
//        this.white    = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "White", Colors.METAL_MOD_WHITE, 1F, 0.8F, 1.25F, 1.0F).setLevelMod(0.8F);
//        this.black    = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Black", Colors.METAL_MOD_BLACK, 1F, 0.8F, 1.0F, 0.8F).setLevelMod(1.5F);
//        this.ancient  = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Ancient", Colors.METAL_MOD_ANCIENT, 1F, 1.0F, 0.5F, 0.5F).setLevelMod(2F);
//        this.shadow   = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Shadow", Colors.METAL_MOD_SHADOW, 1F, 1.0F, 2.0F, 2.0F).setLevelMod(3F);
//        this.dark     = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Dark", Colors.METAL_MOD_DARK, 1F, 1.25F, 0.8F, 1.0F).setLevelMod(1.2F);
//        this.twilight = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Twilight", Colors.METAL_MOD_TWILIGHT, 1F, 1.25F, 1.0F, 1.25F).setLevelMod(3.2F);
//        this.ghost    = new MaterialModifier(MaterialLibrary.getMetalLibrary(), "Ghost", Colors.METAL_MOD_GHOST, 1F, 1.5F, 2.0F, 3.0F).setLevelMod(3.8F);

//        for (String s1 : syllablesFirst) {
//            for (String s2 : syllablesSecond) {
//                for (String s3 : syllablesThird) {
//                    String name = s1 + s2 + s3;
//                    long hash = name.hashCode();
//                    long seed = Main.theWorld.getSeed();
//                    long code = hash % seed;
//
//                    int r = 60 + ((int) Math.abs(code & s1.hashCode())) & 255;
//                    int g = 60 + ((int) Math.abs(code & s2.hashCode())) & 255;
//                    int b = 60 + ((int) Math.abs(code & s3.hashCode())) & 255;
//
//                    int level = (int) Math.abs(code % 41) + 20;
//
//                    Color c = new Color(r, g, b);
//                    float weight = (float) (Math.abs(code % 5) + 1) / 5F;
//
//                    float str = (float) ((Math.abs(code) % 16) + 5) / 10F;
//                    float rng = (float) (((Math.abs(code) + 7) % 16) + 5) / 10F;
//                    float wkn = str * rng;
//
//                    MaterialBase base = new MaterialBase(MaterialLibrary.getMetalLibrary(), name, c, weight, str, rng, wkn, level);
//                }
//            }
//        }
    }
}
