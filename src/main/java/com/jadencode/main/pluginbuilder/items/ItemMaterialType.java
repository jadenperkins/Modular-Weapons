package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.jadencode.main.pluginbuilder.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemMaterialType extends Item {

    private final String scriptName;

    public ItemMaterialType(String name, String script) {
        super(name);
        this.scriptName = script;
    }
    public String getScriptName() {
        return this.scriptName;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json).add("script", this.scriptName);
    }
}