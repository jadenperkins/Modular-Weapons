package com.jadencode.main.stat;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase, Stat> stats = new HashMap<>();

    public <T> StatSet add(StatBase<T> stat, Stat<T> s) {
        this.stats.put(stat, s);
        return this;
    }
    public <T> StatSet add(StatBase<T> stat) {
        return this.add(stat, stat.getDefaultValue());
    }
    public <T> StatSet addVal(StatBase<T> stat, T val) {
        return this.add(stat, stat.from(val));
    }
    public <T> StatSet copy(StatBase<T> stat, StatSet other, Function<Stat<T>, Stat<T>> fun) {
        return this.add(stat, fun.apply(other.get(stat)));
    }
    public <T> StatSet copyVal(StatBase<T> stat, StatSet other, Function<T, T> fun) {
        return this.addVal(stat, fun.apply(other.value(stat)));
    }
    public <A> Stat<A> get(StatBase<A> key) {
        return key.getDefaultValue().getClass().cast(this.stats.getOrDefault(key, key.getDefaultValue()));
    }
    public <A> A value(StatBase<A> key) {
        return this.get(key).get();
    }
    public StatSet copy() {
        StatSet set = new StatSet();
        set.stats.putAll(this.stats);
        return set;
    }
    public StatSet scaled(int i) {
        StatSet ret = new StatSet();
        for(StatBase stat : this.getStatsRaw().keySet()) {
            Stat s = stat.scale(i, this.get(stat));
            ret.add(stat, s);
        }
        return ret;
    }
    public HashMap<StatBase, Stat> getStatsRaw() {
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