package com.jadencode.main.material.libraries;

import com.jadencode.main.material.MaterialBase;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.material.MaterialModifier;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 6/8/2015.
 */
public class GemLibrary extends MaterialLibrary {

    private static final GemLibrary instance = new GemLibrary();

    public MaterialBase jade;
    public MaterialBase topaz;
    public MaterialBase garnet;
    public MaterialBase amethyst;
    public MaterialBase sapphire;
    public MaterialBase emerald;
    public MaterialBase ruby;
    public MaterialBase diamond;

    public MaterialModifier none;
    public MaterialModifier dark;
    public MaterialModifier rough;
    public MaterialModifier vibrant;
    public MaterialModifier faded;
    public MaterialModifier dull;

    private static final List<String> syllablesFirst  = Arrays.asList("Blood", "Dragon", "Shadow", "Sky", "Thunder");
    private static final List<String> syllablesSecond = Arrays.asList("stone", "rock", "gem", "spar", "quartz", "glass", "jewel");

    private GemLibrary() {
        super("Gem");
    }

    public static GemLibrary getInstance() {
        return instance;
    }

    @Override
    public void loadMaterials() {

//        this.jade = new MaterialBase(MaterialLibrary.getGemLibrary(), "Jade", Colors.GEM_JADE, 128F, 1);
//        this.topaz    = new MaterialBase(MaterialLibrary.getGemLibrary(), "Topaz", Colors.GEM_TOPAZ, 64F, 8);
//        this.garnet   = new MaterialBase(MaterialLibrary.getGemLibrary(), "Garnet", Colors.GEM_GARNET, 32F, 15);
//        this.amethyst = new MaterialBase(MaterialLibrary.getGemLibrary(), "Amethyst", Colors.GEM_AMETHYST, 16F, 22);
//        this.sapphire = new MaterialBase(MaterialLibrary.getGemLibrary(), "Sapphire", Colors.GEM_SAPPHIRE, 8F, 29);
//        this.emerald  = new MaterialBase(MaterialLibrary.getGemLibrary(), "Emerald", Colors.GEM_EMERALD, 4F, 36);
//        this.ruby     = new MaterialBase(MaterialLibrary.getGemLibrary(), "Ruby", Colors.GEM_RUBY, 2F, 43);
//        this.diamond  = new MaterialBase(MaterialLibrary.getGemLibrary(), "Diamond", Colors.GEM_DIAMOND, 1F, 50);
//
//        this.none    = new MaterialModifier(MaterialLibrary.getGemLibrary(), "", null, 10F).setLevelMod(1F);
//        this.dark    = new MaterialModifier(MaterialLibrary.getGemLibrary(), "Dark", Colors.GEM_MOD_DARK, 1F).setLevelMod(3F);
//        this.rough   = new MaterialModifier(MaterialLibrary.getGemLibrary(), "Rough", Colors.GEM_MOD_ROUGH, 1F).setLevelMod(2F);
//        this.vibrant = new MaterialModifier(MaterialLibrary.getGemLibrary(), "Vibrant", Colors.GEM_MOD_VIBRANT, 1F).setLevelMod(4F);
//        this.faded   = new MaterialModifier(MaterialLibrary.getGemLibrary(), "Faded", Colors.GEM_MOD_FADED, 1F).setLevelMod(1.5F);
//        this.dull    = new MaterialModifier(MaterialLibrary.getGemLibrary(), "Dull", Colors.GEM_MOD_DULL, 1F).setLevelMod(0.8F);


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