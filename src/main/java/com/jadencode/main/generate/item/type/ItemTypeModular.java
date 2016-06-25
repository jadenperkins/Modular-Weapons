package com.jadencode.main.generate.item.type;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.item.WeaponPartInstance;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.instance.ItemModular;
import com.jadencode.main.generate.item.instance.ItemPart;
import com.jadencode.main.scripts.ScriptItem;
import com.jadencode.main.stat.StatSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemTypeModular extends ItemType<ItemModular> {
    private final ItemPartType primaryPartType;
    private final List<ItemPartType> itemPartTypes;

    public ItemTypeModular(String name, float w, StatSet stats, ItemPartType primary, List<ItemPartType> types, ScriptItem s) {
        super(name, w, stats, s);
        this.primaryPartType = primary;
        this.itemPartTypes = types;
    }
    public ItemPartType getPrimaryPartType() {
        return primaryPartType;
    }
    public List<ItemPartType> getItemPartTypes() {
        return this.itemPartTypes;
    }
    @Override
    public ItemModular create(Random r, int level) {
        return null;
    }
    @Override
    public ItemModular scaled(ItemModular original, int i) {
        List<ItemPart> parts = new ArrayList<>();
        original.getPartsList().forEach(p -> parts.add(p.getItemType().scaled(p, i)));
        return new ItemModular(this, parts);
    }
    @Override
    public List<String> getDisplayInfo(ItemModular instance) {
        List<String> ret = new ArrayList<>();
        instance.getPartsList().forEach(part -> ret.addAll(part.getDisplayInfo()));
        return ret;
    }
    @Override
    public QualityLevel getQualityLevel(ItemModular instance) {
        return QualityLevel.calculate(instance);
    }
}