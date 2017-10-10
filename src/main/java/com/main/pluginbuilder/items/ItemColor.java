package com.main.pluginbuilder.items;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemColor extends Item {
    public int red;
    public int green;
    public int blue;
    public ItemColor(String name, String owner, int r, int g, int b) {
        super(name, owner);
        this.red = r;
        this.green = g;
        this.blue = b;
    }
    @Override
    public void toJson(JsonObject json) {
        JsonArray rgb = new JsonArray();
        rgb.add(new JsonPrimitive(this.red));
        rgb.add(new JsonPrimitive(this.green));
        rgb.add(new JsonPrimitive(this.blue));
        json.add("rgb", rgb);
    }
}