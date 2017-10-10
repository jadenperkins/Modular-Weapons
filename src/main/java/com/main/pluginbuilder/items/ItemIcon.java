package com.main.pluginbuilder.items;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemIcon extends Item {
    public final String base64;
    public ItemIcon(String name, String owner, String base64) {
        super(name, owner);
        this.base64 = base64;
    }
    public String getBase64() {
        return base64;
    }
    @Override
    public void toJson(JsonObject json) {
        json.add("base64", new JsonPrimitive(this.base64));
    }
}