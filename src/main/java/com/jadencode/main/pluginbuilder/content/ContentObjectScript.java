package com.jadencode.main.pluginbuilder.content;

import com.google.gson.JsonObject;
import com.jadencode.main.util.JsonHelper;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ContentObjectScript extends ContentObject {

    private final String scriptType;
    private final String scriptContents;

    public ContentObjectScript(String name, String owner, String type, String contents) {
        super(name, owner);
        this.scriptType = type;
        this.scriptContents = contents;
    }

    public String getScriptType() {
        return scriptType;
    }

    public String getScriptContents() {
        return scriptContents;
    }

    @Override
    public void toJson(JsonObject json) {
        new JsonHelper(json).add("type", this.scriptType).add("script", this.scriptContents);
    }
}