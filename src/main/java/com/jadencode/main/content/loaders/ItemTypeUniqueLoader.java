package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Icons;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.type.ItemTypeUnique;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.JsonHelper;

import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeUniqueLoader extends ContentManager {

    public ItemTypeUniqueLoader() {
        super(Strings.Loaders.UNIQUE_ITEM_TYPES, 5);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString(Strings.JsonKey.STATS));
        ScriptItem script = ItemTypes.script(helper.getString(Strings.JsonKey.SCRIPT));
        float weight = helper.getFloat(Strings.JsonKey.WEIGHT, 1F);
        String info = helper.getString(Strings.JsonKey.ITEM_INFO);
        BufferedImage icon = Icons.get(helper.getString(Strings.JsonKey.ICON));
        QualityLevel level = QualityLevel.valueOf(helper.getString(Strings.JsonKey.QUALITY));

        ItemTypeUnique itemType = new ItemTypeUnique(name, weight, stats, info, level, icon, script);
        ItemTypes.register(itemType);
    }
}