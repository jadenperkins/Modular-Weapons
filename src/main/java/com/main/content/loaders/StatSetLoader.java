package com.main.content.loaders;

import com.main.constants.StatSets;
import com.main.constants.Stats;
import com.main.content.Plugin;
import com.main.pipeline.PipelineObjectStatSet;
import com.main.stat.StatSet;

import java.util.Map;
import java.util.Set;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class StatSetLoader extends ContentManager<PipelineObjectStatSet> {
    public StatSetLoader() {
        super("stat_sets", 4, Plugin::getStatSets);
    }
    @Override
    public void consume(PipelineObjectStatSet object) {
        StatSet set = new StatSet();
        Map<String, Double> stats = object.getStats();
        Set<String> statNames = stats.keySet();
        for (String stat : statNames) {
            set.add(Stats.get(stat), stats.get(stat));
        }
        StatSets.register(object.getName(), set);
    }
}