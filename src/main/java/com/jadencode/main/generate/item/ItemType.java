package com.jadencode.main.generate.item;

import com.jadencode.main.scripts.ScriptWeapon;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jaden on 5/28/2015.
 */
public class ItemType implements WeightedItem {

    private final String                            weaponTypeName;
    private final StatSet                           statSet;
    private final ItemPartType primaryPartType;
    private final List<ItemPartType>              weaponPartTypes;
    private final float                             weight;
    private final ScriptWeapon                      script;

    public ItemType(String name, float w, StatSet stats, ItemPartType primary, List<ItemPartType> types, ScriptWeapon s) {
        this.weaponTypeName = name;
        this.weight = w;
        this.primaryPartType = primary;
        this.weaponPartTypes = types;
        this.statSet = stats;
        this.script = s;
    }
    public String getDisplayName(ItemInstance weapon) {
        return this.script.getDisplayName(weapon);
    }
    public String getWeaponTypeName() {
        return weaponTypeName;
    }
    public ItemPartType getPrimaryPartType() {
        return primaryPartType;
    }
    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet determineStats(ItemInstance instance) {
        List<StatSet> others = instance.getPartsList().stream().map(ItemPartInstance::getStats).collect(Collectors.toList());
        StatSet baseStats = this.getStatSet().scaled(instance.getLevel()).combine(others);
        return baseStats;
    }
    public List<ItemPartType> getWeaponPartTypes() {
        return this.weaponPartTypes;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }
}