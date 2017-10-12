package com.main.pipeline;

import java.util.List;

public class PipelineObjectWeaponType extends PipelineObjectScripted {
    private final String stats;
    private final float weight;
    private final List<String> parts;
    private final String primary;

    public PipelineObjectWeaponType(String name, String script, String stats, float weight, List<String> parts, String primary) {
        super(name, script);
        this.stats = stats;
        this.weight = weight;
        this.parts = parts;
        this.primary = primary;
    }

    public String getStats() {
        return stats;
    }
    public float getWeight() {
        return weight;
    }
    public List<String> getParts() {
        return parts;
    }
    public String getPrimary() {
        return primary;
    }
}
