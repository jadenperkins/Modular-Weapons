package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef<T> implements StatBase<T> {
    
    private final Stat<T> defaultValue;
    private final String statName;
    private final BiFunction<T, T, T> combiner;
    private final BiFunction<Integer, T, T> scaler;

    public StatDef(String s, T val, BiFunction<Integer, T, T> sc, BiFunction<T, T, T> co) {
        this.defaultValue = new Stat<>(val);
        this.statName = s;
        this.scaler = sc;
        this.combiner = co;
    }
    @Override
    public Stat<T> from(T val) {
        return new Stat<>(val);
    }
    @Override
    public Stat<T> getDefaultValue() {
        return this.defaultValue;
    }
    @Override
    public Stat<T> scale(int i, Stat<T> original) {
        return this.from(this.scaler.apply(i, original.get()));
    }
    @Override
    public String getStatName() {
        return this.statName;
    }
    @Override
    public Stat<T> combine(Stat<T> first, Stat<T> second) {
        return this.from(this.combiner.apply(first.get(), second.get()));
    }
}
