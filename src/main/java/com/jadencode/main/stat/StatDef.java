package com.jadencode.main.stat;

import com.jadencode.main.ScriptStat;
import com.jadencode.main.constants.Stats;
import com.jadencode.main.material.Material;

import java.util.function.BiFunction;

/**
 * Created by gtrpl on 6/5/2016.
 */
public class StatDef implements StatBase {
    
    private final double defaultValue;
    private final String statName;
    private final String scriptName;

    public StatDef(String s, String script, double val) {
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
    public double combine(double first, double second) {
        if(this.scriptName == null || Stats.script(this.scriptName) == null) return first + second;
        return Stats.script(this.scriptName).combine(first, second);
    }
    @Override
    public double modify(Material resource, double original) {
        return original * resource.getMultiplier();
    }
    @Override
    public double scale(int i, double original) {
        if(this.scriptName == null || Stats.script(this.scriptName) == null) return original;
        return Stats.script(this.scriptName).scale(i, original);
    }
}
