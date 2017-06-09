package com.jadencode.main.generate.item.instance;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.generate.QualityObject;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemTypeModular;
import com.jadencode.main.stat.StatSet;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 6/24/2016.
 */
public class ItemModular extends Item<ItemTypeModular> implements QualityObject {
    private final List<ItemPart> itemParts;
    private final HashMap<ItemPartType, ItemPart> mappedParts;

    public ItemModular(ItemTypeModular type, List<ItemPart> itemParts, StatSet stats) {
        super(type, getLevel(itemParts), stats);
        this.itemParts = itemParts;
        this.mappedParts = new HashMap<>();
        itemParts.forEach(part -> this.mappedParts.put(part.getItemType().getType(), part));
    }

    private static int getLevel(List<ItemPart> parts) {
        //return Collections.max(parts.stream().map(WeaponPartInstance::getLevel).collect(Collectors.toList()));
        return (int) Math.ceil(Math.sqrt(parts.stream().mapToInt(part -> (int) Math.pow(part.getLevel(), 2)).sum() / parts.size()));
    }

    public List<ItemPart> getPartsList() {
        return this.itemParts;
    }

    public ItemPart getPart(ItemPartType type) {
        return this.mappedParts.get(type);
    }

    @Override
    public List<QualityLevel> getQualityLevels() {
        return this.itemParts.stream().map(ItemPart::getQualityLevel).collect(Collectors.toList());
    }
}