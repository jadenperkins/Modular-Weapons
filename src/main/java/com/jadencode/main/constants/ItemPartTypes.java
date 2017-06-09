package com.jadencode.main.constants;

import com.jadencode.main.generate.item.base.ItemPartType;

import java.util.HashMap;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class ItemPartTypes {

    private static final HashMap<String, ItemPartType> PART_TYPES = new HashMap<>();

    public static ItemPartType get(String name) {
        return PART_TYPES.get(name);
    }

    public static void register(ItemPartType type) {
        PART_TYPES.put(type.getTypeName(), type);
    }
}
