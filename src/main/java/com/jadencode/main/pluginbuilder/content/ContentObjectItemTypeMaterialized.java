package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectItemTypeMaterialized extends ContentObject {

    private final String statSetName;
    private final String scriptName;
    private final float weight;
    private final String description;
    private final List<String> materialTypes;

    public ContentObjectItemTypeMaterialized(String name, String owner, String stats, String script, float weight, String info, List<String> types) {
        super(name, owner);
        this.statSetName = stats;
        this.scriptName = script;
        this.weight = weight > 0 ? weight : 0F;
        this.description = info;
        this.materialTypes = types;
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

    public String getDescription() {
        return description;
    }

    public List<String> getMaterialTypes() {
        return this.materialTypes;
    }

    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("stats", this.statSetName)
                .add("script", this.scriptName)
                .add("weight", this.weight)
                .add("itemInfo", this.description)
                .add("materials", JsonHelper.toArray(this.materialTypes));
    }
}