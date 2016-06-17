package com.jadencode.main.content.scripts.loaders;

import com.jadencode.main.content.scripts.ScriptStat;
import com.jadencode.main.constants.Stats;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStatLoader extends ScriptLoader<ScriptStat> {
    public ScriptStatLoader() {
        super("stats", ScriptStat.class, Stats.getScripts());
    }
}