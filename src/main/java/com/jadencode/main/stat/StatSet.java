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

    public StatSet add(StatBase stat, Stat s) {
        this.stats.put(stat, s);
        return this;
    }
    public StatSet add(StatBase stat, float f) {
        return this.add(stat, new StatFloat(f));
    }
    public StatSet add(StatBase stat, double d) {
        return this.add(stat, new StatDouble(d));
    }
    public StatSet add(StatBase stat, int i) {
        return this.add(stat, new StatInt(i));
    }
    public StatSet add(StatBase stat, String s) {
        return this.add(stat, new StatString(s));
    }
    public Stat get(StatBase key) {
        return this.stats.getOrDefault(key, key.getDefaultValue());
    }
    public float getFloat(StatBase<StatFloat> key) {
        return this.get(key).getAsFloat().getValue();
    }
    public int getInt(StatBase<StatInt> key) {
        return this.get(key).getAsInteger().getValue();
    }
    public double getDouble(StatBase<StatDouble> key) {
        return this.get(key).getAsDouble().getValue();
    }
    public String getString(StatBase<StatString> key) {
        return this.get(key).getAsString().getValue();
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