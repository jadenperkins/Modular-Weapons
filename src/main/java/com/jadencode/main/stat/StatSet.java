package com.jadencode.main.stat;

import java.util.HashMap;

/**
 * Created by Jaden on 9/17/2015.
 */
public class StatSet {
    private final HashMap<StatBase<?>, Stat<?>> statsRaw = new HashMap();

    public <T> StatSet add(StatBase<T> statBase, Stat<T> stat) {
        this.statsRaw.put(statBase, stat);
        return this;
    }

    public <T> StatSet addVal(StatBase<T> stat, T value) {
        return this.add(stat, stat.from(value));
    }

    public <T> Stat<T> get(StatBase<T> statBase) {
        return statBase.getDefaultValue().getClass().cast(this.statsRaw.getOrDefault(statBase, statBase.getDefaultValue()));
    }

    public <A> A value(StatBase<A> key) {
        return this.get(key).get();
    }

    public StatSet copy() {
        StatSet set = new StatSet();
        set.statsRaw.putAll(this.statsRaw);
        return set;
    }

    public StatSet combine(StatSet other) {
        StatSet retSet = new StatSet();
        this.statsRaw.forEach((k, v) -> retSet.add(k, combine(k, v, other.get(k))));
        return retSet;
    }

    public Stat combine(StatBase statBase, Stat first, Stat second) {
        return statBase.combine(first, second);
    }
}