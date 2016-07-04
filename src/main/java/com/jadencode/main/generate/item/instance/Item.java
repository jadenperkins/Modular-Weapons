package com.jadencode.main.generate.item.instance;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.type.ItemType;
import com.jadencode.main.stat.StatSet;

import java.util.List;

/**
 * Created by gtrpl on 6/24/2016.
 */
public abstract class Item<T extends ItemType> {
    private final T itemType;
    private final int level;
    private final StatSet statSet;

    public Item(T type, int level, StatSet stats) {
        this.itemType = type;
        this.level = level;
        this.statSet = stats;
    }

    public Item(T type, int level) {
        this(type, level, type.getStatSet().scaled(level));
    }

    public int getLevel() {
        return this.level;
    }

    public T getItemType() {
        return this.itemType;
    }

    public StatSet getStatSet() {
        return this.statSet;
    }

    public String getDisplayName() {
        return this.itemType.getDisplayName(this);
    }

    public void printItemCard() {
        this.itemType.printItemCard(this);
    }

    public List<String> getDisplayInfo() {
        return this.itemType.getDisplayInfo(this);
    }

    public QualityLevel getQualityLevel() {
        return this.itemType.getQualityLevel(this);
    }
}
