package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemPartType extends Item {

    private final String iconName;

    public ItemPartType(String name, String icon) {
        super(name);
        this.iconName = icon;
    }
    public String getIconName() {
        return this.iconName;
    }
    @Override
    public void toJson(JsonObject json) {
        json.add("icon", new JsonPrimitive(this.iconName));
    }
}