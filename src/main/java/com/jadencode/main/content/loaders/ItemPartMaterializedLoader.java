package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.item.Joint;
import com.jadencode.main.generate.item.base.ItemPartBase;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.JsonHelper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemPartMaterializedLoader extends ContentManager {
    public ItemPartMaterializedLoader() {
        super("Materialized Item Parts", 8);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        String nameMod = helper.getString("nameMod");
        String partInfo = helper.getString("partInfo", "An item part");
        StatSet set = StatSets.get(helper.getString("stats"));
        float weight = helper.getFloat("weight", 1F);
        BufferedImage icon = Icons.get(helper.getString("icon", null));
        ItemPartType type = ItemPartTypes.get(helper.getString("partType"));
        ScriptItem script = ItemTypes.script(helper.getString("script"));
        List<Joint> joints = new ArrayList<>();
        JsonArray array = helper.getArray("joints");
        for (JsonElement jsonElement : array) {
            JsonHelper object = new JsonHelper(jsonElement.getAsJsonObject());
            joints.add(new Joint(object.getString("name"), object.getDouble("x"), object.getDouble("y")));
        }

        List<MaterialType> types = JsonHelper.fromArray(helper.getArray("materials")).stream().map(MaterialTypes::get).collect(Collectors.toList());
        ItemPartBase partBase = new ItemPartBase(name, nameMod, partInfo, weight, set, type, icon, types, script, joints);
        ItemParts.addBasePart(partBase);
    }
}