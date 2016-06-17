package com.jadencode.main;

import com.jadencode.main.content.scripts.ScriptBase;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStat extends ScriptBase {
    public ScriptStat(String fileName, String _spellString) {
        super(fileName, _spellString);
    }
    public double combine(double first, double second) {
        return this.invokeWithDefault("combine", first + second, first, second);
    }
    public double scale(int level, double value) {
        return this.invokeWithDefault("scale", value, level, value);
    }
}