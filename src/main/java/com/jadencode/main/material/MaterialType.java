package com.jadencode.main.material;

import com.jadencode.main.Main;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.scripts.ScriptMaterialType;

import java.awt.*;
import java.util.List;

/**
 * Created by Jaden on 2/10/2015.
 */
public class MaterialType {
    private final String name;
    private final ScriptMaterialType script;

    public MaterialType(String name, ScriptMaterialType script) {
        this.name = name;
        this.script = script;
    }
    public String getName() {
        return this.name;
    }
    public void generateExotics() {
        List<String> exoticNames = this.script.getExoticNames();
        for (String name : exoticNames) {
            long hash = name.hashCode();
            long seed = Main.theWorld.getSeed();
            long code = hash % seed;

            int color = (int)(code % 16777216);
            int level = (int) Math.abs(code % 41) + 20;

            Color c = new Color(color);
            float weight = (float) (Math.abs(code % 5) + 1) / 5F;
            float mod = (float) (Math.abs((code + 7) % 4) + 1) / 2F;
            Materials.register(this, new Material(name, c, weight, mod, level, this));
        }
    }
}