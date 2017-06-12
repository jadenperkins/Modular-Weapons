package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.item.base.ItemMaterializedBase;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.JsonHelper;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeMaterializedLoader extends ContentManager {

    public ItemTypeMaterializedLoader() {
        super(Strings.Loaders.MATERIALIZED_ITEM_TYPES, 5);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);

        String info = helper.getString(Strings.JsonKey.ITEM_INFO);
        float weight = helper.getFloat(Strings.JsonKey.WEIGHT, 1F);
        StatSet stats = StatSets.get(helper.getString(Strings.JsonKey.STATS));
        BufferedImage icon = Icons.get(helper.getString(Strings.JsonKey.ICON));
        ScriptItem script = ItemTypes.script(helper.getString(Strings.JsonKey.SCRIPT));
        List<MaterialType> types = JsonHelper.fromArray(helper.getArray(Strings.JsonKey.MATERIALS)).stream().map(MaterialTypes::get).collect(Collectors.toList());

        ItemMaterializedBase base = new ItemMaterializedBase(name, info, weight, stats, icon, script, types);

        ItemTypes.addMaterializedBase(base);
    }
}