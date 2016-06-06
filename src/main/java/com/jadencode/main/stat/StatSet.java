package com.jadencode.main.stat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase, Stat> stats = new HashMap<>();

    public <T extends Stat> StatSet add(StatBase<T> stat, T s) {
        this.stats.put(stat, s);
        return this;
    }
    public Stat get(StatBase key) {
        return this.stats.get(key);
    }
    public StatSet copy() {
        StatSet set = new StatSet();
        set.stats.putAll(this.stats);
        return set;
    }
    public HashMap<StatBase, Stat> getStatsRaw() {
        return this.stats;
    }
}