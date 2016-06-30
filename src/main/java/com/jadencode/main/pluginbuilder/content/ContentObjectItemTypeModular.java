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
    private final String anchorPart;
    private final List<String> requiredParts;
    private final List<String> optionalParts;

    public ContentObjectItemTypeModular(String name, String owner, String stats, String script, float weight, String primary, String anchor, List<String> parts, List<String> opts) {
        super(name, owner);
        this.statSetName = stats;
        this.scriptName = script;
        this.weight = weight > 0 ? weight : 0F;
        this.primaryPart = primary;
        this.anchorPart = anchor;
        this.requiredParts = parts;
        this.optionalParts = opts;
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
    public String getAnchorPart() {
        return anchorPart;
    }
    public List<String> getRequiredParts() {
        return this.requiredParts;
    }
    public List<String> getOptionalParts() {
        return this.optionalParts;
    }
    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json)
                .add("stats", this.statSetName)
                .add("script", this.scriptName)
                .add("weight", this.weight)
                .add("primary", this.primaryPart)
                .add("anchor", this.anchorPart)
                .add("parts", JsonHelper.toArray(this.requiredParts))
                .add("optional parts", JsonHelper.toArray(this.optionalParts));
    }
}