package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/9/2016.
 */
public class StatBase<T> {
    private final String name;
    private final Stat<T> defaultValue;
    private final Function<T, Stat<T>> generator;
    private final BiFunction<T, T, T> combine;

    public StatBase(String s, T val, Function<T, Stat<T>> cl, BiFunction<T, T, T> co) {
        this.name = s;
        this.generator = cl;
        this.combine = co;
        this.defaultValue = this.from(val);
    }
    public Stat<T> from(T val) {
        return this.generator.apply(val);
    }
    public String getName() {
        return name;
    }
    public Stat<T> getDefaultValue() {
        return defaultValue;
    }
    public Stat<T> combine(Stat<T> first, Stat<T> second) {
        return from(this.combine.apply(first.get(), second.get()));
    }
}