package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Stats;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.scripts.ScriptStat;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatDef;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatLoader extends ContentManager {

    public StatLoader() {
        super(Strings.Loaders.STATS, 3);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        double defaultValue = obj.get(Strings.JsonKey.DEFAULT).getAsDouble();
        String scriptName = obj.has(Strings.JsonKey.SCRIPT) ? obj.get(Strings.JsonKey.SCRIPT).getAsString() : null;
        ScriptStat script = Stats.script(scriptName);
        StatBase stat = new StatDef(name, defaultValue, script);
        Stats.register(stat);
    }
}