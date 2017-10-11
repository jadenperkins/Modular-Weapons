package com.main.pipeline;

public class PipelineObjectStat extends PipelineObjectScripted {
    private final double defaultValue;

    public PipelineObjectStat(String name, String script, double defaultValue) {
        super(name, script);
        this.defaultValue = defaultValue;
    }
    public double getDefaultValue() {
        return defaultValue;
    }
}
