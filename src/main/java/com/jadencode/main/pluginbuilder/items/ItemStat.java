package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemStat extends Item {

    private final String scriptName;
    private final double defaultValue;

    public ItemStat(String name, String icon, double value) {
        super(name);
        this.scriptName = icon;
        this.defaultValue = value;
    }
    public String getScriptName() {
        return this.scriptName;
    }
    public double getDefaultValue() {
        return this.defaultValue;
    }
    @Override
    public void toJson(JsonObject json) {
        json.add("default", new JsonPrimitive(this.defaultValue));
        if(this.scriptName != null && !this.scriptName.isEmpty())
            json.add("script", new JsonPrimitive(this.scriptName));
    }
}