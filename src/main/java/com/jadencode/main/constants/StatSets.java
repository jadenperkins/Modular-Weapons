package com.jadencode.main.constants;

import com.jadencode.main.stat.StatSet;

import java.util.HashMap;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class StatSets {
    private static final StatSet EMPTY = new StatSet();
    private static final HashMap<String, StatSet> STAT_SETS = new HashMap<>();
    public static void register(String name, StatSet set) {
        STAT_SETS.put(name, set);
    }
    public static StatSet get(String name) {
        return STAT_SETS.getOrDefault(name, EMPTY);
    }
}
