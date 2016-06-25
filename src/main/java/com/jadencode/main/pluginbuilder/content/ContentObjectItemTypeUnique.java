package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectItemTypeUnique extends ContentObject {

    private final String statSetName;
    private final String scriptName;
    private final float weight;
    private final String iconName;
    private final String qualityLevel;
    private final String description;

    public ContentObjectItemTypeUnique(String name, String owner, String stats, String script, float weight, String icon, String quality, String info) {
        super(name, owner);
        this.statSetName = stats;
        this.scriptName = script;
        this.weight = weight > 0 ? weight : 1;
        this.iconName = icon;
        this.qualityLevel = quality.equals("") ? "LEGENDARY" : quality;
        this.description = info;
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
    public String getQualityLevel() {
        return qualityLevel;
    }
    public String getDescription() {
        return description;
    }
    public String getIconName() {
        return iconName;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("stats", this.statSetName)
                .add("script", this.scriptName)
                .add("weight", this.weight)
                .add("icon", this.iconName)
                .add("quality", this.qualityLevel)
                .add("itemInfo", this.description);
    }
}