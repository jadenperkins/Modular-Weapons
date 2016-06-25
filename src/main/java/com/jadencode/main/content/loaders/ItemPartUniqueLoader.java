package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemTypePartUnique;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemPartUniqueLoader extends ContentManager {
    public ItemPartUniqueLoader() {
        super("Unique Item Parts", 8);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        String nameMod = obj.get("nameMod").getAsString();
        String partInfo = obj.has("partInfo") ? obj.get("partInfo").getAsString() : "A item part";
        StatSet set = obj.has("stats") ? StatSets.get(obj.get("stats").getAsString()) : StatSets.EMPTY;
        float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        String iconName = obj.has("icon") ? obj.get("icon").getAsString() : null;
        BufferedImage icon = Icons.get(iconName);
        ItemPartType type = ItemPartTypes.get(obj.get("partType").getAsString());
        QualityLevel qualityLevel = QualityLevel.valueOf(obj.get("quality").getAsString());
        ScriptItem script = ItemTypes.script(obj.get("script").getAsString());

        ItemTypePartUnique part = new ItemTypePartUnique(name, nameMod, partInfo, set, weight, script, qualityLevel, icon, type);
        ItemParts.register(part);
    }
}