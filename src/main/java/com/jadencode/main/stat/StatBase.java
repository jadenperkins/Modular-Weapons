package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/9/2016.
 */
public class StatBase<T> {
    private final String name;
    private final Stat<T> defaultValue;
    private final Function<T, T> cloner;
    private final BiFunction<T, T, T> combine;

    public StatBase(String s, T val, Function<T, T> cl, BiFunction<T, T, T> co) {
        this.name = s;
        this.cloner = cl;
        this.combine = co;
        this.defaultValue = this.makeStatInstance(val);
    }
    public <T> Stat<T> makeStatInstance(T val) {
        return null;
    }
    public String getName() {
        return name;
    }
    public Stat<T> getDefaultValue() {
        return defaultValue;
    }
    public T copy() {
        return this.cloner.apply(this.getDefaultValue().get());
    }
    public Stat<T> combine(Stat<T> first, Stat<T> second) {
        return makeStatInstance(this.combine.apply(first.get(), second.get()));
    }
}