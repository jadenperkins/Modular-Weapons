package com.jadencode.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
    @Override
    public void toJson(JsonObject json) {
        json.add("type", new JsonPrimitive(this.scriptType));
        json.add("script", new JsonPrimitive(this.scriptContents));
    }
}