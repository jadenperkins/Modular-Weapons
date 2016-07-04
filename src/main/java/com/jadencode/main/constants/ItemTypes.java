package com.jadencode.main.constants;

import com.jadencode.main.generate.item.base.ItemMaterializedBase;
import com.jadencode.main.generate.item.type.ItemType;
import com.jadencode.main.generate.item.type.ItemTypeMaterialized;
import com.jadencode.main.generate.item.type.ItemTypeModular;
import com.jadencode.main.generate.item.type.ItemTypeUnique;
import com.jadencode.main.scripts.ScriptItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/11/2016.
 */
public final class ItemTypes {
    private static final List<ItemMaterializedBase> MATERIALIZED_BASES = new ArrayList<>();
    private static final HashMap<String, ItemType> ITEM_TYPES = new HashMap<>();
    private static final HashMap<String, ItemTypeModular> MODULAR_TYPES = new HashMap<>();
    private static final HashMap<String, ItemTypeUnique> UNIQUE_TYPES = new HashMap<>();
    private static final HashMap<String, ItemTypeMaterialized> MATERIALIZED_TYPES = new HashMap<>();

    private static final HashMap<String, ScriptItem> SCRIPTS = new HashMap<>();

    public static HashMap<String, ScriptItem> getScripts() {
        return SCRIPTS;
    }

    public static ScriptItem script(String name) {
        return SCRIPTS.getOrDefault(name, SCRIPTS.get(null));
    }

    public static List<ItemType> getItemTypes() {
        return new ArrayList<>(ITEM_TYPES.values());
    }

    public static List<ItemType> getModularTypes() {
        return new ArrayList<>(MODULAR_TYPES.values());
    }

    public static List<ItemType> getUniqueTypes() {
        return new ArrayList<>(UNIQUE_TYPES.values());
    }

    public static List<ItemType> getMaterializedTypes() {
        return new ArrayList<>(MATERIALIZED_TYPES.values());
    }


    public static void register(ItemType type) {
        ITEM_TYPES.put(type.getItemBaseName(), type);
    }

    public static void register(ItemTypeModular type) {
        register((ItemType) type);
        MODULAR_TYPES.put(type.getItemBaseName(), type);
    }

    public static void register(ItemTypeUnique type) {
        register((ItemType) type);
        UNIQUE_TYPES.put(type.getItemBaseName(), type);
    }

    public static void register(ItemTypeMaterialized type) {
        register((ItemType) type);
        MATERIALIZED_TYPES.put(type.getItemBaseName(), type);
    }

    public static ItemType get(String name) {
        return ITEM_TYPES.get(name);
    }

    public static List<ItemMaterializedBase> getMaterializedBases() {
        return MATERIALIZED_BASES;
    }

    public static void addMaterializedBase(ItemMaterializedBase part) {
        MATERIALIZED_BASES.add(part);
    }

    public static void generateMaterializedItems() {
        ItemTypes.getMaterializedBases()
                .forEach(item -> item.getMaterials()
                        .forEach(type -> Materials.getMaterials(type)
                                .forEach(material -> register(new ItemTypeMaterialized(item, material)))));
    }
}