package com.main.constants;

import com.main.scripts.ScriptStat;
import com.main.stat.StatBase;

import java.util.HashMap;


/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {
    private static final HashMap<String, StatBase> STATS = new HashMap<>();
    private static final HashMap<String, ScriptStat> SCRIPTS = new HashMap<>();

    public static HashMap<String, ScriptStat> getScripts() {
        return SCRIPTS;
    }
    public static ScriptStat script(String name) {
        return SCRIPTS.getOrDefault(name, SCRIPTS.get(null));
    }
    public static void register(StatBase stat) {
        STATS.put(stat.getStatName(), stat);
    }
    public static StatBase get(String name) {
        return STATS.get(name);
    }
}
