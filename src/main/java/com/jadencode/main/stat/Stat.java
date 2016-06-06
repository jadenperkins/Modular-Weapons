package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class Stat {
    public boolean isInteger() {
        return false;
    }
    public boolean isFloat() {
        return false;
    }
    public boolean isDouble() {
        return false;
    }
    public StatInt getAsInteger() {
        throw new IllegalArgumentException();
    }
    public StatDouble getAsDouble() {
        throw new IllegalArgumentException();
    }
    public StatFloat getAsFloat() {
        throw new IllegalArgumentException();
    }
}