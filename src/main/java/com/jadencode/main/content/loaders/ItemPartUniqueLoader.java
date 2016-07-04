package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.Joint;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemTypePartUnique;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.JsonHelper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemPartUniqueLoader extends ContentManager {
    public ItemPartUniqueLoader() {
        super("Unique Item Parts", 8);
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
        QualityLevel qualityLevel = QualityLevel.valueOf(helper.getString("quality"));
        ScriptItem script = ItemTypes.script(helper.getString("script"));
        List<Joint> joints = new ArrayList<>();
        JsonArray jointArray = helper.getArray("joints");
        for (JsonElement jsonElement : jointArray) {
            JsonHelper object = new JsonHelper(jsonElement.getAsJsonObject());
            joints.add(new Joint(object.getString("name"), object.getDouble("x"), object.getDouble("y")));
        }

        ItemTypePartUnique part = new ItemTypePartUnique(name, nameMod, partInfo, set, weight, script, qualityLevel, icon, type, joints);
        ItemParts.register(part);
    }
}