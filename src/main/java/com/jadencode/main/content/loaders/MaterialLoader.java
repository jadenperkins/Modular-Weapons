package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Colors;
import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialType;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialLoader extends ContentManager {


    public MaterialLoader() {
        super(Strings.Loaders.MATERIALS, 7);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        Color c = Colors.get(obj.get(Strings.JsonKey.COLOR).getAsString());
        float w = obj.has(Strings.JsonKey.WEIGHT) ? obj.get(Strings.JsonKey.WEIGHT).getAsFloat() : 1F;
        float m = obj.get(Strings.JsonKey.MODIFIER).getAsFloat();
        int l = obj.get(Strings.JsonKey.LEVEL).getAsInt();
        MaterialType t = MaterialTypes.get(obj.get(Strings.JsonKey.MATERIAL).getAsString());
        Material material = new Material(name, c, w, m, l, QualityLevel.COMMON, t);
        Materials.register(t, material);
    }
}