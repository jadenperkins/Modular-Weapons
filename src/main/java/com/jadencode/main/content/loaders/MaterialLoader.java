package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Colors;
import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialLoader extends ContentManager {
    public MaterialLoader() {
        super("Materials", 7);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        Color c = Colors.get(obj.get("color").getAsString());
        float w = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        float m = obj.get("mod").getAsFloat();
        int l = obj.get("level").getAsInt();
        MaterialType t = MaterialTypes.get(obj.get("material").getAsString());
        Material material = new Material(name, c, w, m, l, QualityLevel.COMMON, t);
        Materials.register(t, material);
    }
}