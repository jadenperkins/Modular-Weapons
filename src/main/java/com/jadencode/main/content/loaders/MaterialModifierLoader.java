package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.Colors;
import com.jadencode.main.constants.MaterialModifiers;
import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.material.MaterialModifier;
import com.jadencode.main.material.MaterialType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialModifierLoader extends ContentManager {

    public MaterialModifierLoader() {
        super(Strings.Loaders.MODIFIERS, 6);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        Color c = Colors.get(obj.get(Strings.JsonKey.COLOR).getAsString());
        float w = obj.has(Strings.JsonKey.WEIGHT) ? obj.get(Strings.JsonKey.WEIGHT).getAsFloat() : 1F;
        float m = obj.get(Strings.JsonKey.MODIFIER).getAsFloat();
        float l = obj.get(Strings.JsonKey.LEVEL).getAsFloat();
        JsonArray materials = obj.get(Strings.JsonKey.MATERIAL).getAsJsonArray();
        List<String> mats = new ArrayList<>();
        materials.forEach(mat -> mats.add(mat.getAsString()));
        List<MaterialType> materialTypes = mats.stream().map(MaterialTypes::get).collect(Collectors.toList());
        MaterialModifier modifier = new MaterialModifier(name, c, m, l, w, materialTypes);

        materialTypes.forEach(type -> MaterialModifiers.register(type, modifier));
    }
}