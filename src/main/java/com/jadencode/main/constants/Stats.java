package com.jadencode.main.constants;

import com.jadencode.main.stat.StatBase;

import java.util.HashMap;


/**
 * Created by gtrpl on 6/11/2016.
 */
public final class Stats {

    private static final HashMap<String, StatBase> STATS = new HashMap<>();

    public static void register(StatBase stat) {
        STATS.put(stat.getStatName(), stat);
    }
    public static StatBase get(String name) {
        return STATS.get(name);
    }
}
