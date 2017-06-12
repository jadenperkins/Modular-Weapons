package com.jadencode.main.constants;

import com.jadencode.main.generate.item.base.ItemPartBase;
import com.jadencode.main.generate.item.base.ItemPartType;
import com.jadencode.main.generate.item.type.ItemTypePart;
import com.jadencode.main.generate.item.type.ItemTypePartMaterialized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class ItemParts {
    private static final List<ItemPartBase> ITEM_PARTS = new ArrayList<>();
    private static final HashMap<ItemPartType, List<ItemTypePart>> PARTS_LISTS = new HashMap<>();
    private static final HashMap<String, ItemTypePart> MAPPED_PARTS = new HashMap<>();

    public static void register(ItemTypePart part) {
        ItemTypes.register(part);
        getPartsList(part.getType()).add(part);
        MAPPED_PARTS.put(part.getItemBaseName(), part);
    }

    public static ItemTypePart get(String name) {
        return MAPPED_PARTS.get(name);
    }

    public static List<ItemPartBase> getBaseParts() {
        return ITEM_PARTS;
    }

    public static void addBasePart(ItemPartBase part) {
        ITEM_PARTS.add(part);
    }

    public static void generateItemParts() {
        ItemParts.getBaseParts()
                .forEach(basePart -> basePart.getMaterials()
                        .forEach(type -> Materials.getMaterials(type)
                                .forEach(material -> register(new ItemTypePartMaterialized(basePart, material)))));
    }

    public static List<ItemTypePart> getPartsList(ItemPartType type) {
        if (PARTS_LISTS.containsKey(type)) return PARTS_LISTS.get(type);
        List<ItemTypePart> parts = new ArrayList<>();
        PARTS_LISTS.put(type, parts);
        return parts;
    }
}