package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypePartUnique extends ItemTypePart {
    private final String       nameMod;
    private final List<String> partInfo;

    public ItemTypePartUnique(String partName, String nameMod, String partInfo, StatSet statSet, float weight, ScriptItem s, QualityLevel level, BufferedImage icon, ItemPartType type) {
        super(partName, weight, statSet, s, icon, level, Color.WHITE, type);
        this.nameMod = nameMod;
        this.partInfo = Arrays.asList(partInfo);
    }
    @Override
    public String getNameMod() {
        return nameMod;
    }

    @Override
    public List<String> getDisplayInfo(ItemPart instance) {
        return partInfo;
    }
    @Override
    public Color getColor() {
        return null;
    }
    @Override
    public String getMaterialName() {
        return "";
    }
}