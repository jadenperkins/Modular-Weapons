package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDouble extends Stat<Double> {
    public StatDouble(double val) {
        super(val);
    }
    @Override
    public Stat<Double> add(Stat<Double> other) {
        return new StatDouble(this.get() + other.get());
    }
}