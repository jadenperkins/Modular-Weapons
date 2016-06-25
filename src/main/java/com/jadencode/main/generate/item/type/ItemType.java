package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.util.WeightedItem;

import java.util.List;
import java.util.Random;

/**
 * Created by gtrpl on 6/24/2016.
 */
public abstract class ItemType<T extends Item> implements WeightedItem {
    private final String itemBaseName;
    private final StatSet statSet;
    private final float weight;
    private final ScriptItem script;

    public ItemType(String name, float w, StatSet stats, ScriptItem s) {
        this.itemBaseName = name;
        this.weight = w;
        this.statSet = stats;
        this.script = s;
    }
    public String getDisplayName(T instance) {
        return this.script.getDisplayName(instance, this.getDisplayFallback(instance));
    }
    public String getDisplayFallback(T instance) {
        return this.getItemBaseName();
    }

    public String getItemBaseName() {
        return itemBaseName;
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    @Override
    public float getWeight() {
        return this.weight;
    }

    public abstract T create(Random r, int level);
    public abstract T scaled(T original, int i);
    public abstract List<String> getDisplayInfo(T instance);
    public abstract QualityLevel getQualityLevel(T instance);
}