package com.jadencode.main.stat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase<?>, Stat<?>> stats = new HashMap<>();

    public StatSet add(StatBase<?> stat, Stat<?> s) {
        this.stats.put(stat, s);
        return this;
    }
    public <T> StatSet add(StatBase<T> stat, T val) {
        return this.add(stat, stat.from(val));
    }
    public <A> Stat<A> get(StatBase<A> key) {
        return key.getDefaultValue().getClass().cast(this.stats.getOrDefault(key, key.getDefaultValue()));
    }
    public <A> A value(StatBase<A> key) {
        return this.get(key).get();
    }
    public float getFloat(StatBase<Stat<Float>> key) {
        return this.get(key).as(StatFloat.class).get();
    }
    public int getInt(StatBase<Stat<Integer>> key) {
        return this.get(key).as(StatInt.class).get();
    }
    public double getDouble(StatBase<Stat<Double>> key) {
        return this.get(key).as(StatDouble.class).get();
    }
    public StatSet copy() {
        StatSet set = new StatSet();
        set.stats.putAll(this.stats);
        return set;
    }
    public HashMap<StatBase<?>, Stat<?>> getStatsRaw() {
        return this.stats;
    }
}