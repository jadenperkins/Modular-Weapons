package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.instance.ItemUnique;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypeUnique extends ItemType<ItemUnique> {
    private final List<String> displayInfo;
    private final QualityLevel itemQuality;
    public ItemTypeUnique(String name, float w, StatSet stats, String info, QualityLevel level, BufferedImage icon, ScriptItem s) {
        super(name, w, stats, icon, null, s);
        this.displayInfo = Arrays.asList(info);
        this.itemQuality = level;
    }
    @Override
    public ItemUnique create(Random r, int level) {
        return new ItemUnique(this);
    }
    @Override
    public ItemUnique scaled(ItemUnique original, int i) {
        return new ItemUnique(this);
    }
    @Override
    public List<String> getDisplayInfo(ItemUnique instance) {
        return this.displayInfo;
    }
    @Override
    public QualityLevel getQualityLevel(ItemUnique instance) {
        return this.itemQuality;
    }
}