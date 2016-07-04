package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.Colors;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class ColorLoader extends ContentManager {
    public ColorLoader() {
        super("Colors", 2);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        JsonArray rgb = obj.get("rgb").getAsJsonArray();
        Color c = new Color(rgb.get(0).getAsInt(), rgb.get(1).getAsInt(), rgb.get(2).getAsInt());
        Colors.register(name, c);
    }
}