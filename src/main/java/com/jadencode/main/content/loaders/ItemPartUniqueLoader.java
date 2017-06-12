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
        super(Strings.Loaders.UNIQUE_ITEM_PARTS, 8);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        String nameMod = helper.getString(Strings.JsonKey.NAME_MOD);
        String partInfo = helper.getString(Strings.JsonKey.PART_INFO, Strings.AN_ITEM_PART);
        StatSet set = StatSets.get(helper.getString(Strings.JsonKey.STATS));
        float weight = helper.getFloat(Strings.JsonKey.WEIGHT, 1F);
        BufferedImage icon = Icons.get(helper.getString(Strings.JsonKey.ICON, null));
        ItemPartType type = ItemPartTypes.get(helper.getString(Strings.JsonKey.PART_TYPE));
        QualityLevel qualityLevel = QualityLevel.valueOf(helper.getString(Strings.JsonKey.QUALITY));
        ScriptItem script = ItemTypes.script(helper.getString(Strings.JsonKey.SCRIPT));
        List<Joint> joints = new ArrayList<>();
        JsonArray jointArray = helper.getArray(Strings.JsonKey.JOINTS);
        for (JsonElement jsonElement : jointArray) {
            JsonHelper object = new JsonHelper(jsonElement.getAsJsonObject());
            joints.add(new Joint(object.getString(Strings.JsonKey.NAME), object.getDouble(Strings.JsonKey.COORD_X), object.getDouble(Strings.JsonKey.COORD_Y)));
        }

        ItemTypePartUnique part = new ItemTypePartUnique(name, nameMod, partInfo, set, weight, script, qualityLevel, icon, type, joints);
        ItemParts.register(part);
    }
}