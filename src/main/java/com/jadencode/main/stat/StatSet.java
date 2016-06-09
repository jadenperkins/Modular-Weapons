package com.jadencode.main.stat;

import com.jadencode.main.generate.weapon.WeaponInstance;
import com.jadencode.main.generate.weapon.WeaponPartInstance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase, Stat> stats = new HashMap<>();

    public <T> StatSet add(StatBase<T> stat, Stat<T> s) {
        this.stats.put(stat, s);
        return this;
    }
    public <T> StatSet addVal(StatBase<T> stat, T val) {
        return this.add(stat, stat.from(val));
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
    public HashMap<StatBase, Stat> getStatsRaw() {
        return this.stats;
    }
    public StatSet combine(StatSet other) {
        StatSet ret = new StatSet();
        for(StatBase stat : this.getStatsRaw().keySet()) {
            ret.add(stat, this.get(stat).add(other.get(stat)));
        }
        return ret;
    }
}