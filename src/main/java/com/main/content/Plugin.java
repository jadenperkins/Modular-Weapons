package com.main.content;

import com.main.pipeline.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/20/2016.
 */
public class Plugin implements Comparable<Plugin> {
    private final String pluginName;
    private final List<String> dependencies = new ArrayList<>();

    private final List<PipelineObjectColor> colors = new ArrayList<>();
    private final List<PipelineObjectIcon> icons = new ArrayList<>();
    private final List<PipelineObjectMaterial> materials = new ArrayList<>();
    private final List<PipelineObjectMaterialModifier> materialModifiers = new ArrayList<>();
    private final List<PipelineObjectMaterialType> materialTypes = new ArrayList<>();
    private final List<PipelineObjectPartType> partTypes = new ArrayList<>();
    private final List<PipelineObjectScript> scripts = new ArrayList<>();
    private final List<PipelineObjectStat> stats = new ArrayList<>();
    private final List<PipelineObjectStatSet> statSets = new ArrayList<>();
    private final List<PipelineObjectWeaponPart> weaponParts = new ArrayList<>();
    private final List<PipelineObjectWeaponType> weaponTypes = new ArrayList<>();

    public Plugin(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginName() {
        return pluginName;
    }
    public List<String> getDependencies() {
        return dependencies;
    }

    public List<PipelineObjectColor> getColors() {
        return colors;
    }
    public List<PipelineObjectIcon> getIcons() {
        return icons;
    }
    public List<PipelineObjectMaterial> getMaterials() {
        return materials;
    }
    public List<PipelineObjectMaterialModifier> getMaterialModifiers() {
        return materialModifiers;
    }
    public List<PipelineObjectMaterialType> getMaterialTypes() {
        return materialTypes;
    }
    public List<PipelineObjectPartType> getPartTypes() {
        return partTypes;
    }
    public List<PipelineObjectScript> getScripts() {
        return scripts;
    }
    public List<PipelineObjectStat> getStats() {
        return stats;
    }
    public List<PipelineObjectStatSet> getStatSets() {
        return statSets;
    }
    public List<PipelineObjectWeaponPart> getWeaponParts() {
        return weaponParts;
    }
    public List<PipelineObjectWeaponType> getWeaponTypes() {
        return weaponTypes;
    }

    @Override
    public int compareTo(Plugin o) {
        return this.dependencies.contains(o.pluginName) ? 1 : -1;
    }
}