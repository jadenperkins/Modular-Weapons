package com.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.main.constants.Colors;
import com.main.constants.MaterialModifiers;
import com.main.constants.MaterialTypes;
import com.main.material.MaterialModifier;
import com.main.material.MaterialType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialModifierLoader extends ContentManager {
    public MaterialModifierLoader() {
        super("MaterialBase Modifiers", 6);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        Color c = Colors.get(obj.get("color").getAsString());
        float w = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        float l = obj.get("level").getAsFloat();
        float m = obj.get("mod").getAsFloat();
        JsonArray materials = obj.get("materials").getAsJsonArray();
        List<String> mats = new ArrayList<>();
        materials.forEach(mat -> mats.add(mat.getAsString()));
        List<MaterialType> materialTypes = mats.stream().map(MaterialTypes::get).collect(Collectors.toList());
        MaterialModifier modifier = new MaterialModifier(name, c, w, m, l, materialTypes);

        materialTypes.forEach(type -> MaterialModifiers.register(type, modifier));
    }
}