package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatInt extends Stat<Integer> {
    public StatInt(int val) {
        super(val);
    }
    @Override
    public Stat<Integer> add(Stat<Integer> other) {
        return new StatInt(this.get() + other.get());
    }
}