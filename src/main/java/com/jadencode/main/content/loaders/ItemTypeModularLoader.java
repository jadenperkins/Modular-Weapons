package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.ItemPartTypes;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.Strings;
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
        super(Strings.Loaders.MODULAR_ITEM_TYPES, 5);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString(Strings.JsonKey.STATS));
        ScriptItem script = ItemTypes.script(helper.getString(Strings.JsonKey.SCRIPT));
        float weight = helper.getFloat(Strings.JsonKey.WEIGHT, 1F);
        List<ItemPartType> types = JsonHelper.fromArray(helper.getArray(Strings.JsonKey.PARTS)).stream().map(ItemPartTypes::get).collect(Collectors.toList());
        List<ItemPartType> opts = JsonHelper.fromArray(helper.getArray(Strings.JsonKey.OPTIONAL_PARTS)).stream().map(ItemPartTypes::get).collect(Collectors.toList());
        ItemPartType primary = obj.has(Strings.JsonKey.PRIMARY) ? ItemPartTypes.get(helper.getString(Strings.JsonKey.PRIMARY)) : types.get(0);
        ItemPartType anchor = obj.has(Strings.JsonKey.ANCHOR) ? ItemPartTypes.get(helper.getString(Strings.JsonKey.ANCHOR)) : primary;

        ItemTypeModular itemType = new ItemTypeModular(name, weight, stats, primary, anchor, types, opts, script);
        ItemTypes.register(itemType);
    }
}