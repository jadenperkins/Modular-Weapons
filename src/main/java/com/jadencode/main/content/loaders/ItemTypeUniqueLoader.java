package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.type.ItemTypeUnique;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeUniqueLoader extends ContentManager {
    public ItemTypeUniqueLoader() {
        super("Unique Item Types", 5);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString("stats"));
        ScriptItem script = ItemTypes.script(helper.getString("script"));
        float weight = helper.getFloat("weight", 1F);
        String info = helper.getString("itemInfo");
        QualityLevel level = QualityLevel.valueOf(helper.getString("quality"));

        ItemTypeUnique itemType = new ItemTypeUnique(name, weight, stats, info, level, script);
        ItemTypes.register(itemType);
    }
}