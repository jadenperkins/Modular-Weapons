package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * Created by gtrpl on 6/24/2016.
 */
public abstract class ItemTypePart extends ItemType<ItemPart> {
    private final BufferedImage icon;
    private final QualityLevel qualityLevel;
    private final ItemPartType type;

    public ItemTypePart(String name, float w, StatSet stats, ScriptItem s, BufferedImage icon, QualityLevel level, ItemPartType type) {
        super(name, w, stats, s);
        this.icon = icon;
        this.qualityLevel = level;
        this.type = type;
    }
    public ItemPartType getType() {
        return type;
    }
    public BufferedImage getIcon() {
        return this.icon == null ? this.getType().getIcon() : this.icon;
    }

    @Override
    public ItemPart create(Random r, int level) {
        return new ItemPart(this, level);
    }
    @Override
    public ItemPart scaled(ItemPart original, int i) {
        return new ItemPart(this, i);
    }
    @Override
    public QualityLevel getQualityLevel(ItemPart instance) {
        return this.qualityLevel;
    }

    public abstract String getNameMod();
    public abstract Color getColor();
    public abstract String getMaterialName();
}