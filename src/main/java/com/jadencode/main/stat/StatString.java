package com.jadencode.main.stat;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatString extends Stat {
    private final String value;

    public StatString(String s) {
        this.value = s;
    }
    public String getValue() {
        return this.value;
    }
    @Override
    public boolean isString() {
        return true;
    }
    @Override
    public StatString getAsString() {
        return this;
    }
}
