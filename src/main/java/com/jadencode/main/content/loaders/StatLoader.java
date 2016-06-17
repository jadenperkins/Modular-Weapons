package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Stats;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatNonScaled;
import com.jadencode.main.stat.StatScaled;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatLoader extends ContentManager {
    public StatLoader() {
        super("Stats", 3);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        double defaultValue = obj.get("default").getAsDouble();
        boolean scale = obj.get("scale").getAsBoolean();
        StatBase stat = scale ? new StatScaled(name, defaultValue) : new StatNonScaled(name, defaultValue);
        Stats.register(stat);
    }
}