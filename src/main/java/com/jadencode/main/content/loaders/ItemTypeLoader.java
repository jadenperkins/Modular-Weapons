package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.PartTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.generate.item.ItemPartType;
import com.jadencode.main.generate.item.ItemType;
import com.jadencode.main.pluginbuilder.JsonHelper;
import com.jadencode.main.scripts.ScriptWeapon;
import com.jadencode.main.stat.StatSet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeLoader extends ContentManager {
    public ItemTypeLoader() {
        super("Item Types", 5);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString("stats"));
        ScriptWeapon script = ItemTypes.script(helper.getString("script"));
        float weight = helper.getFloat("weight", 1F);
        List<ItemPartType> types = JsonHelper.fromArray(helper.getArray("parts")).stream().map(PartTypes::get).collect(Collectors.toList());
        ItemPartType primary = obj.has("primary") ? PartTypes.get(helper.getString("primary")) : types.get(0);

        ItemType weapon = new ItemType(name, weight, stats, primary, types, script);
        ItemTypes.register(weapon);
    }
}