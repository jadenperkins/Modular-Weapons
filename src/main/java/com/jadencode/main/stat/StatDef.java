package com.jadencode.main.stat;

import com.jadencode.main.material.Material;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef implements StatBase {
    
    private final double defaultValue;
    private final String statName;
    private final boolean levelDependent;

    public StatDef(String s, double val, boolean scale) {
        this.defaultValue = val;
        this.statName = s;
        this.levelDependent = scale;
    }
    @Override
    public double getDefaultValue() {
        return this.defaultValue;
    }
    @Override
    public double scale(int i, double original) {
        if(!this.levelDependent) return original;
        return original * Math.pow(1.1D, i - 1);
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
