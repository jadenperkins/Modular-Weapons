package com.jadencode.main.stat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase, Object> stats = new HashMap<>();

    public <T> StatSet add(StatBase<T> stat, T s) {
        this.stats.put(stat, s);
        return this;
    }
    public <T> StatSet add(StatBase<T> stat) {
        return this.add(stat, stat.getDefaultValue());
    }
    public <T> StatSet copy(StatBase<T> stat, StatSet other) {
        return this.add(stat, other.get(stat));
    }
    public <A> A get(StatBase<A> key) {
        return (A)this.stats.getOrDefault(key, key.getDefaultValue());
    }
    public StatSet copy() {
        StatSet set = new StatSet();
        set.stats.putAll(this.stats);
        return set;
    }
    public StatSet scaled(int i) {
        StatSet ret = new StatSet();
        for(StatBase stat : this.getStatsRaw().keySet()) {
            Object s = stat.scale(i, this.get(stat));
            ret.add(stat, s);
        }
        return ret;
    }
    public HashMap<StatBase, Object> getStatsRaw() {
        return this.stats;
    }
    public StatSet combine(Collection<StatSet> others) {
        StatSet ret = this.copy();
        for(StatBase stat : this.getStatsRaw().keySet()) {
            for(StatSet other : others) {
                ret.add(stat, stat.combine(ret.get(stat), other.get(stat)));
            }
        }
        return ret;
    }
}