package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatInt extends Stat {
    private final int value;

    public StatInt(int i) {
        this.value = i;
    }
    public int getValue() {
        return this.value;
    }
    @Override
    public boolean isInteger() {
        return true;
    }
    @Override
    public StatInt getAsInteger() {
        return this;
    }
}