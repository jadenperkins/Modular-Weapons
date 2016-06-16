package com.jadencode.main.stat;

import com.jadencode.main.material.Material;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/5/2016.
 */
public abstract class StatDef implements StatBase {
    
    private final double defaultValue;
    private final String statName;

    public StatDef(String s, double val) {
        this.defaultValue = val;
        this.statName = s;
    }
    @Override
    public double getDefaultValue() {
        return this.defaultValue;
    }
    @Override
    public String getStatName() {
        return this.statName;
    }
    @Override
    public double combine(double first, double second) {
        return first + second;
    }
    @Override
    public double modify(Material resource, double original) {
        return original * resource.getMultiplier();
    }
}
