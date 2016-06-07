package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatFloat extends Stat<Float> {
    public StatFloat(float val) {
        super(val);
    }
    @Override
    public Stat<Float> add(Stat<Float> other) {
        return new StatFloat(this.get() + other.get());
    }
}