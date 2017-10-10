package com.main.stat;

import com.main.material.Material;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase, Double> stats = new HashMap<>();

    public StatSet add(StatBase stat, double s) {
        this.stats.put(stat, s);
        return this;
    }
    public StatSet add(StatBase stat) {
        return this.add(stat, stat.getDefaultValue());
    }
    public StatSet copy(StatBase stat, StatSet other) {
        return this.add(stat, other.get(stat));
    }
    public double get(StatBase key) {
        return this.stats.getOrDefault(key, key.getDefaultValue());
    }
    public StatSet copy() {
        StatSet set = new StatSet();
        set.stats.putAll(this.stats);
        return set;
    }
    public StatSet scaled(int i) {
        StatSet ret = new StatSet();
        for(StatBase stat : this.getStatsRaw().keySet()) {
            double s = stat.scale(i, this.get(stat));
            ret.add(stat, s);
        }
        return ret;
    }
    public HashMap<StatBase, Double> getStatsRaw() {
        return this.stats;
    }
    public StatSet combine(Collection<StatSet> others) {
        StatSet ret = this.copy();
        for (StatBase stat : this.getStatsRaw().keySet()) {
            for (StatSet other : others) {
                ret.add(stat, stat.combine(ret.get(stat), other.get(stat)));
            }
        }
//        this.getStatsRaw().keySet().forEach(stat -> others.forEach(other -> ret.add(stat, stat.combine(ret.get(stat), other.get(stat)))));
        return ret;
    }
    public StatSet modified(Material resource) {
        StatSet ret = this.copy();
        this.getStatsRaw().keySet().forEach(stat -> ret.add(stat, stat.modify(resource, ret.get(stat))));
        return ret;
    }
}