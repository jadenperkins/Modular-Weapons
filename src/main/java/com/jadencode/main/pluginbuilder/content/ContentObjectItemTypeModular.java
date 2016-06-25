package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

import java.util.List;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectItemTypeModular extends ContentObject {

    private final String statSetName;
    private final String scriptName;
    private final float weight;
    private final String primaryPart;
    private final List<String> partTypes;

    public ContentObjectItemTypeModular(String name, String owner, String stats, String script, float weight, String primary, List<String> parts) {
        super(name, owner);
        this.statSetName = stats;
        this.scriptName = script;
        this.weight = weight > 0 ? weight : 1;
        this.primaryPart = primary;
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
    public String getPrimaryPart() {
        return primaryPart;
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
                .add("primary", this.primaryPart)
                .add("parts", JsonHelper.toArray(this.partTypes));
    }
}