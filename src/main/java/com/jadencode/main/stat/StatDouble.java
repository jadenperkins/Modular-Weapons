package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDouble extends Stat {
    private final double value;

    public StatDouble(double d) {
        this.value = d;
    }
    public double getValue() {
        return this.value;
    }
    @Override
    public boolean isDouble() {
        return true;
    }
    @Override
    public StatDouble getAsDouble() {
        return this;
    }
}