package com.jadencode.main.stat;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class Stat<T> {
    private final T value;
    private final Function<T, T> cloner;
    private final BiFunction<T, T, T> combiner;

    public Stat(T val, Function<T, T> cl, BiFunction<T, T, T> co) {
        this.value = val;
        this.cloner = cl;
        this.combiner = co;
    }
    public T get() {
        return this.value;
    }
    public <A extends Stat<?>> boolean is(Class<A> c) {
        return c.isAssignableFrom(this.getClass());
    }
    public <A extends Stat<?>> A as(Class<A> c) {
        return c.cast(this);
    }
}