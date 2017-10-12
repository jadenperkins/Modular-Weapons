package com.main.pipeline;

import java.util.Map;

public class PipelineObjectStatSet extends PipelineObject {
    private final Map<String, Double> stats;

    public PipelineObjectStatSet(String name, Map<String, Double> stats) {
        super(name);
        this.stats = stats;
    }
    public Map<String, Double> getStats() {
        return stats;
    }
}
