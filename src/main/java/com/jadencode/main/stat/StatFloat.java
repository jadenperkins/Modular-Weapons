package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatFloat extends Stat {
    private final float value;

    public StatFloat(float f) {
        this.value = f;
    }
    public float getValue() {
        return this.value;
    }
    @Override
    public boolean isFloat() {
        return true;
    }
    @Override
    public StatFloat getAsFloat() {
        return this;
    }
}