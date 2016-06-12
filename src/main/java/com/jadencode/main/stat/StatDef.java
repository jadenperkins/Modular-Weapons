package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef<T> implements StatBase<T> {
    
    private final T defaultValue;
    private final String statName;
    private final BiFunction<T, T, T> combiner;
    private final BiFunction<Integer, T, T> scaler;

    public StatDef(String s, T val, BiFunction<Integer, T, T> sc, BiFunction<T, T, T> co) {
        this.defaultValue = val
        this.statName = s;
        this.scaler = sc;
        this.combiner = co;
    }
    @Override
    public T getDefaultValue() {
        return this.defaultValue;
    }
    @Override
    public T scale(int i, T original) {
        return this.scaler.apply(i, original);
    }
    @Override
    public String getStatName() {
        return this.statName;
    }
    @Override
    public T combine(T first, T second) {
        return this.from(this.combiner.apply(first.get(), second.get()));
    }
}
