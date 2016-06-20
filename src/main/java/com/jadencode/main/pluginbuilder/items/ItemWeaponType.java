package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemWeaponType extends Item {

    private final String statSetName;
    private final String scriptName;
    private final List<String> partTypes;

    public ItemWeaponType(String name, String stats, String script, List<String> parts) {
        super(name);
        this.statSetName = stats;
        this.scriptName = script;
        this.partTypes = parts;
    }
    public String getStatSetName() {
        return this.statSetName;
    }
    public String getScriptName() {
        return this.scriptName;
    }
    public List<String> getPartTypes() {
        return this.partTypes;
    }
    @Override
    public void toJson(JsonObject json) {
        if(this.statSetName != null && !this.statSetName.isEmpty())
            json.add("stats", new JsonPrimitive(this.statSetName));

        if(this.scriptName != null && !this.scriptName.isEmpty())
            json.add("script", new JsonPrimitive(this.scriptName));

        JsonArray array = new JsonArray();

        for (String partType : this.partTypes)
            array.add(new JsonPrimitive(partType));

        json.add("parts", array);
    }
}