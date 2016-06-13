package com.jadencode.main.material;

import com.jadencode.main.Main;
import com.jadencode.main.constants.MaterialTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Jaden on 2/10/2015.
 */
public class MaterialType {
    private final String name;
    private final List<String> exoticNames;

    public MaterialType(String name, Supplier<List<String>> s) {
        this.name = name;
        this.exoticNames = s.get();
        MaterialTypes.register(this);
    }
    public String getName() {
        return this.name;
    }
    public void generateExotics() {
        for (String name : exoticNames) {
            long hash = name.hashCode();
            long seed = Main.theWorld.getSeed();
            long code = hash % seed;

            int color = (int)(code % 16777216);
            int level = (int) Math.abs(code % 41) + 20;

            Color c = new Color(color);
            float weight = (float) (Math.abs(code % 5) + 1) / 5F;
            float mod = (float) (Math.abs((code + 7) % 4) + 1) / 2F;
            new Material(name, c, weight, mod, level, this);
        }
    }
}