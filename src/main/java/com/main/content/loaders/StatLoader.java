package com.main.content.loaders;

import com.google.gson.JsonObject;
import com.main.constants.Stats;
import com.main.scripts.ScriptStat;
import com.main.stat.StatBase;
import com.main.stat.StatDef;

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
        String scriptName = obj.has("script") ? obj.get("script").getAsString() : null;
        ScriptStat script = Stats.script(scriptName);
        StatBase stat = new StatDef(name, defaultValue, script);
        Stats.register(stat);
    }
}