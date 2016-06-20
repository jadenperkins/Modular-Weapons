package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemWeaponType extends Item {

    private final String statSetName;
    private final String scriptName;
    private final float weight;
    private final List<String> partTypes;

    public ItemWeaponType(String name, String stats, String script, float weight, List<String> parts) {
        super(name);
        this.statSetName = stats;
        this.scriptName = script;
        this.weight = weight > 0 ? weight : 1;
        this.partTypes = parts;
    }
    public String getStatSetName() {
        return this.statSetName;
    }
    public String getScriptName() {
        return this.scriptName;
    }
    public float getWeight() {
        return this.weight;
    }
    public List<String> getPartTypes() {
        return this.partTypes;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("stats", this.statSetName)
                .add("script", this.scriptName)
                .add("weight", this.weight)
                .add("parts", JsonHelper.toArray(this.partTypes));
    }
}