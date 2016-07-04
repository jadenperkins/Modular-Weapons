package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.ItemPartTypes;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemTypeModular;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.JsonHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeModularLoader extends ContentManager {
    public ItemTypeModularLoader() {
        super("Modular Item Types", 5);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString("stats"));
        ScriptItem script = ItemTypes.script(helper.getString("script"));
        float weight = helper.getFloat("weight", 1F);
        List<ItemPartType> types = JsonHelper.fromArray(helper.getArray("parts")).stream().map(ItemPartTypes::get).collect(Collectors.toList());
        List<ItemPartType> opts = JsonHelper.fromArray(helper.getArray("optional parts")).stream().map(ItemPartTypes::get).collect(Collectors.toList());
        ItemPartType primary = obj.has("primary") ? ItemPartTypes.get(helper.getString("primary")) : types.get(0);
        ItemPartType anchor = obj.has("anchor") ? ItemPartTypes.get(helper.getString("anchor")) : primary;

        ItemTypeModular itemType = new ItemTypeModular(name, weight, stats, primary, anchor, types, opts, script);
        ItemTypes.register(itemType);
    }
}