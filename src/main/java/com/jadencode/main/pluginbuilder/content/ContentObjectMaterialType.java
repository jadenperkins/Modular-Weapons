package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectMaterialType extends ContentObject {

    private final String scriptName;

    public ContentObjectMaterialType(String name, String owner, String script) {
        super(name, owner);
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