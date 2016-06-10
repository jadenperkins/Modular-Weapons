package com.jadencode.main.stat;

import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef<T> implements StatBase<T> {
    
    private final Stat<T> defaultValue;
    private final String statName;
    private final Function<T, Stat<T>> generator;

    public StatDef(String s, Stat<T> val, Function<T, Stat<T>> g) {
        this.defaultValue = val;
        this.statName = s;
        this.generator = g;
    }
    public Stat<T> from(T val) {
        return this.generator.apply(val);
    }
    public Stat<T> getDefaultValue() {
        return this.defaultValue;
    }
    public String getStatName() {
        return this.statName;
    }
}
