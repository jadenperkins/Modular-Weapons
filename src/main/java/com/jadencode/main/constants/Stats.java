package com.jadencode.main.constants;

import com.jadencode.main.ScriptStat;
import com.jadencode.main.stat.StatBase;

import java.util.HashMap;


/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {
    private static final HashMap<String, StatBase> STATS = new HashMap<>();
    private static final HashMap<String, ScriptStat> STAT_SCRIPTS = new HashMap<>();

    public static void addScript(ScriptStat scriptStat) {
        if(!STAT_SCRIPTS.containsKey(scriptStat.getScriptName())) STAT_SCRIPTS.put(scriptStat.getScriptName(), scriptStat);
    }
    public static ScriptStat script(String name) {
        return STAT_SCRIPTS.get(name);
    }
    public static void register(StatBase stat) {
        STATS.put(stat.getStatName(), stat);
    }
    public static StatBase get(String name) {
        return STATS.get(name);
    }
}
