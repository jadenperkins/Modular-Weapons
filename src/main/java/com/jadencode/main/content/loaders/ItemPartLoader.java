package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.item.ItemPart;
import com.jadencode.main.generate.item.ItemPartBase;
import com.jadencode.main.generate.item.ItemPartLegendary;
import com.jadencode.main.generate.item.ItemPartType;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.stat.StatSet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemPartLoader extends ContentManager {
    public ItemPartLoader() {
        super("Item Parts", 8);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        boolean isLegendary = !obj.has("materials");
        String nameMod = obj.get("nameMod").getAsString();
        String partInfo = obj.has("partInfo") ? obj.get("partInfo").getAsString() : "A item part";
        StatSet set = obj.has("stats") ? StatSets.get(obj.get("stats").getAsString()) : StatSets.EMPTY;
        float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        String iconName = obj.has("icon") ? obj.get("icon").getAsString() : null;
        BufferedImage icon = Icons.get(iconName);
        ItemPartType type = PartTypes.get(obj.get("partType").getAsString());

        if(isLegendary) {
            ItemPart part = new ItemPartLegendary(name, nameMod, partInfo, set, weight, icon, type);
            ItemParts.register(part);
        } else {
            JsonArray materials = obj.get("materials").getAsJsonArray();
            List<String> mats = new ArrayList<>();
            materials.forEach(e -> mats.add(e.getAsString()));
            List<MaterialType> types = mats.stream().map(MaterialTypes::get).collect(Collectors.toList());
            ItemPartBase part = new ItemPartBase(name, nameMod, weight, set, type, icon, types);
            ItemParts.addBasePart(part);
        }
    }
}