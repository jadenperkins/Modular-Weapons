package com.jadencode.main.content.scripts;

import com.jadencode.main.scripts.ScriptStat;
import com.jadencode.main.constants.Stats;

import java.util.Arrays;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptStatLoader extends ScriptLoader<ScriptStat> {
    public ScriptStatLoader() {
        super("stats", ScriptStat.class, Stats.getScripts());
    }
}