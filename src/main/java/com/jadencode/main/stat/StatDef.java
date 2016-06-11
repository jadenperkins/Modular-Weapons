package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef<T> implements StatBase<T> {
    
    private final Stat<T> defaultValue;
    private final String statName;
    private final Function<T, Stat<T>> generator;
    private final BiFunction<Integer, Stat<T>, Stat<T>> scaler;
    private final BiFunction<T, Stat<T>, Stat<T>> modifier;

    public StatDef(String s, Stat<T> val, Function<T, Stat<T>> g, BiFunction<Integer, Stat<T>, Stat<T>> sc, BiFunction<T, Stat<T>, Stat<T>> m) {
        this.defaultValue = val;
        this.statName = s;
        this.generator = g;
        this.scaler = sc;
        this.modifier = m;
    }
    public Stat<T> from(T val) {
        return this.generator.apply(val);
    }
    public Stat<T> getDefaultValue() {
        return this.defaultValue;
    }
    public Stat<T> scale(int i, Stat<T> original) {
        return this.scaler.apply(i, original);
    }
    public Stat<T> modify(T val, Stat<T> original) {
        return this.modifier.apply(val, original);
    }
    public String getStatName() {
        return this.statName;
    }
}
