package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class Stat<T> {
    private final T value;

    public Stat(T val) {
        this.value = val;
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