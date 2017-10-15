package com.main.plugins.pipeline;

import java.util.List;

public class PipelineObjectWeaponPart extends PipelineObject {
    private final String nameMod;
    private final String partInfo;
    private final float weight;
    private final String partType;
    private final String stats;
    private final String icon;
    private final List<String> materials;

    public PipelineObjectWeaponPart(String name, String nameMod, String partInfo, float weight, String partType, String stats, String icon, List<String> materials) {
        super(name);
        this.nameMod = nameMod;
        this.partInfo = partInfo;
        this.weight = weight;
        this.partType = partType;
        this.stats = stats;
        this.icon = icon;
        this.materials = materials;
    }

    public List<String> getMaterials() {
        return materials;
    }
    public String getNameMod() {
        return nameMod;
    }
    public String getPartInfo() {
        return partInfo;
    }
    public String getStats() {
        return stats;
    }
    public float getWeight() {
        return weight;
    }
    public String getIcon() {
        return icon;
    }
    public String getPartType() {
        return partType;
    }
}
