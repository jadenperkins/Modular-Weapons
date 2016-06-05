package com.jadencode.main.magic;

import com.jadencode.main.Main;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Jaden on 2/12/2015.
 */
public class SpellObject {

    private SpellBase spellBase;
    private String    name;
    private int       level;

    private HashMap<String, Integer> integers = new HashMap<>();
    private HashMap<String, Float>   floats   = new HashMap<>();
    private HashMap<String, String>  strings  = new HashMap<>();

    public SpellObject(SpellBase spellBase, int level, Random r) {
        this.spellBase = spellBase;
        this.level = level;
        this.name = spellBase.getName(r) + " " + spellBase.getDelivery().getName();
        this.generateAttributes();
    }

    public static SpellObject generateRandom(Random r, int playerLevel) {
        SpellBase base = SpellBase.getRandom(r);
        int min = (int)Math.ceil((double)playerLevel * 0.9D);
        int max = (int)Math.floor((double)playerLevel * 1.1D);
        int level = Math.min(r.nextInt(max - min + 1) + min, Main.LEVEL_CAP);

        SpellObject ret = new SpellObject(base, level, r);
        return ret;
    }
    public static SpellObject generateRandom(int level) {
        return generateRandom(new Random(), level);
    }

    public SpellBase getSpellBase() {
        return this.spellBase;
    }
    private void generateAttributes() {
        this.spellBase.generateAttributes(this);
    }

    public HashMap<String, Float> getFloats() {
        return floats;
    }

    public HashMap<String, Integer> getIntegers() {
        return integers;
    }

    public HashMap<String, String> getStrings() {
        return strings;
    }
    public int getLevel() {
        return this.level;
    }
    public String getName() {
        return this.name;
    }
}