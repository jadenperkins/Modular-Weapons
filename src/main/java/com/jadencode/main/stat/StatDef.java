package com.jadencode.main.stat;

import com.jadencode.main.material.Material;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef<T> implements StatBase<T> {
    
    private final T defaultValue;
    private final String statName;
    private final BiFunction<T, T, T> combiner;
    private final BiFunction<Integer, T, T> scaler;
    private final BiFunction<Float, T, T> modifier;

    public StatDef(String s, T val, BiFunction<Integer, T, T> sc, BiFunction<T, T, T> co, BiFunction<Float, T, T> mo) {
        this.defaultValue = val;
        this.statName = s;
        this.scaler = sc;
        this.combiner = co;
        this.modifier = mo;
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
        return this.combiner.apply(first, second);
    }
    @Override
    public T modify(Material resource, T original) {
        return this.modifier.apply(resource.getMultiplier(), original);
    }
}
