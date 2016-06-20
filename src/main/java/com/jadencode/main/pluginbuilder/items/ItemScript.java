package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.jadencode.main.pluginbuilder.JsonHelper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by gtrpl on 6/19/2016.
 */
public class ItemScript extends Item {

    private final String scriptType;
    private final String scriptContents;

    public ItemScript(String name, String type, String contents) {
        super(name);
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