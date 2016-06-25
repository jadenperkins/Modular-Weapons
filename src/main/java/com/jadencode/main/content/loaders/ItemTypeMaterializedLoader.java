package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.*;
import com.jadencode.main.generate.item.base.ItemMaterializedBase;
import com.jadencode.main.material.MaterialType;
import com.jadencode.main.util.JsonHelper;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ItemTypeMaterializedLoader extends ContentManager {
    public ItemTypeMaterializedLoader() {
        super("Materialized Item Types", 5);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);

        String info = helper.getString("itemInfo");
        float weight = helper.getFloat("weight", 1F);
        StatSet stats = StatSets.get(helper.getString("stats"));
        BufferedImage icon = Icons.get(helper.getString("icon"));
        ScriptItem script = ItemTypes.script(helper.getString("script"));
        List<MaterialType> types = JsonHelper.fromArray(helper.getArray("materials")).stream().map(MaterialTypes::get).collect(Collectors.toList());

        ItemMaterializedBase base = new ItemMaterializedBase(name, info, weight, stats, icon, script, types);

        ItemTypes.addMaterializedBase(base);
    }
}