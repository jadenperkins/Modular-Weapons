package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Icons;
import com.jadencode.main.constants.ItemPartTypes;
import com.jadencode.main.generate.item.base.ItemPartType;

import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class PartTypeLoader extends ContentManager {
    public PartTypeLoader() {
        super("Part Types", 1);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        String iconName = obj.has("icon") ? obj.get("icon").getAsString() : null;
        BufferedImage icon = Icons.get(iconName);
        ItemPartTypes.register(new ItemPartType(name, icon));
    }
}