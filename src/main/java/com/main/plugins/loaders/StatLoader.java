package com.main.plugins.loaders;

import com.main.constants.Stats;
import com.main.plugins.Plugin;
import com.main.plugins.pipeline.PipelineObjectStat;
import com.main.scripts.ScriptStat;
import com.main.stat.StatBase;
import com.main.stat.StatDef;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatLoader extends ContentManager<PipelineObjectStat> {
    public StatLoader() {
        super("stats", 3, Plugin::getStats);
    }
    @Override
    public void consume(PipelineObjectStat object) {
        double defaultValue = object.getDefaultValue();
        String scriptName = object.getScript();
        ScriptStat script = Stats.script(scriptName);
        StatBase stat = new StatDef(object.getName(), defaultValue, script);
        Stats.register(stat);
    }
}