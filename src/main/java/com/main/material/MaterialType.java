package com.main.material;

import com.main.Main;
import com.main.constants.Materials;
import com.main.generate.QualityLevel;
import com.main.scripts.ScriptMaterialType;

import java.awt.*;
import java.util.ArrayList;
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
    public List<Material> generateExotics() {
        List<String> exoticNames = this.script.getExoticNames();
        List<Material> materials = new ArrayList<>();
        for (String name : exoticNames) {
            long hash = name.hashCode();
            long seed = Main.theWorld.getSeed();
            long code = hash % seed;

            int color = (int)(code % 16777216);
            int level = (int) Math.abs(code % 41) + 20;

            Color c = new Color(color);
            float weight = (float) (Math.abs(code % 5) + 1) / 5F;
            float mod = (float) (Math.abs((code + 7) % 4) + 1) / 2F;
            Material material = new MaterialBase(name, c, weight, mod, level, QualityLevel.RARE, this);
            Materials.register(this, material);
            materials.add(material);
        }
        return materials;
    }
}