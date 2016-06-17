package com.jadencode.main.content.scripts;

import com.jadencode.main.ScriptStat;
import com.jadencode.main.constants.Stats;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStatLoader extends ScriptLoader<ScriptBase> {
    @Override
    public String getTypeName() {
        return "stats";
    }
    @Override
    public void load(String fileName, String string) {
        ScriptStat statScript = new ScriptStat(fileName, string);
        Stats.addScript(statScript);
    }
}