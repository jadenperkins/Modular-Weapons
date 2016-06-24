package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemPartType extends Item {

    private final String iconName;

    public ItemPartType(String name, String owner, String icon) {
        super(name, owner);
        this.iconName = icon;
    }
    public String getIconName() {
        return this.iconName;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json).add("icon", this.iconName);
    }
}