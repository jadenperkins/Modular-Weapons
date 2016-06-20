package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
        if(this.scriptName != null && !this.scriptName.isEmpty())
            json.add("script", new JsonPrimitive(this.scriptName));
    }
}