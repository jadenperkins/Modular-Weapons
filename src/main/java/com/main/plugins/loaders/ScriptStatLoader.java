package com.main.plugins.loaders;

import com.main.constants.Stats;
import com.main.scripts.ScriptStat;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStatLoader extends ScriptLoader<ScriptStat> {
    public ScriptStatLoader() {
        super("stats", ScriptStat.class, Stats.getScripts());
    }
}