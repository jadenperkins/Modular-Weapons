package com.main.scripts;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStat extends ScriptBase {
    public ScriptStat(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public double combine(double first, double second) {
        return this.invokeWithDefault("combine", first + second, first, second);
    }
    public double scale(int level, double value) {
        return this.invokeWithDefault("scale", value, level, value);
    }
}