package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Icons;
import com.jadencode.main.constants.ItemPartTypes;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.util.JsonHelper;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class PartTypeLoader extends ContentManager {
    public PartTypeLoader() {
        super("Part Types", 1);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        BufferedImage icon = Icons.get(helper.getString("icon", null));
        List<String> joints = JsonHelper.fromArray(helper.getArray("joints"));
        ItemPartTypes.register(new ItemPartType(name, icon, joints));
    }
}