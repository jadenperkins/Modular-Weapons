package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.Stats;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.stat.StatSet;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatSetLoader extends ContentManager {

    public StatSetLoader() {
        super(Strings.Loaders.STAT_SETS, 4);
    }

    @Override
    public void consume(String name, JsonObject obj) {
        StatSet set = new StatSet();
        JsonArray stats = obj.get(Strings.JsonKey.STATS).getAsJsonArray();
        for (JsonElement stat : stats) {
            JsonObject json = stat.getAsJsonObject();
            String statName = json.get(Strings.JsonKey.STAT).getAsString();
            double value = json.get(Strings.JsonKey.VALUE).getAsDouble();
            set.add(Stats.get(statName), value);
        }
        StatSets.register(name, set);
    }
}