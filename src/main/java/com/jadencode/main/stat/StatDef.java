package com.jadencode.main.stat;

import com.jadencode.main.constants.Stats;
import com.jadencode.main.material.Material;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef implements StatBase {
    
    private final double defaultValue;
    private final String statName;
    private final String scriptName;

    public StatDef(String s, double val, String script) {
        this.defaultValue = val;
        this.statName = s;
        this.scriptName = script;
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
    public double modify(Material resource, double original) {
        return original * resource.getMultiplier();
    }
    @Override
    public double combine(double first, double second) {
        return Stats.script(this.scriptName).combine(first, second);
    }
    @Override
    public double scale(int i, double original) {
        return Stats.script(this.scriptName).scale(i, original);
    }
}
