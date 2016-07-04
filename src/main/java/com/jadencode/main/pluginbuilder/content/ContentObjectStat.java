package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectStat extends ContentObject {

    private final String scriptName;
    private final double defaultValue;

    public ContentObjectStat(String name, String owner, String script, double value) {
        super(name, owner);
        this.scriptName = script;
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
        new JsonHelper(json).add("default", this.defaultValue).add("script", this.scriptName);
    }
}