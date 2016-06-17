package com.jadencode.main.constants;

import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class MaterialTypes {

    private static final HashMap<String, MaterialType> MATERIAL_TYPES = new HashMap<>();

    private static Supplier<List<String>> supplier(List<String> f, List<String> m, List<String> l) {
        return () -> {
            List<String> ret = new ArrayList<>();
            f.forEach(fs -> m.forEach(ms -> l.forEach(ls -> ret.add(fs + ms + ls))));
            return ret;
        };
    }

    private static Supplier<List<String>> supplier(List<String> f, List<String> l) {
        return () -> {
            List<String> ret = new ArrayList<>();
            f.forEach(fs -> l.forEach(ls -> ret.add(fs + ls)));
            return ret;
        };
    }
    public static void register(MaterialType type) {
        MATERIAL_TYPES.put(type.getName(), type);
    }
    public static MaterialType get(String name) {
        return MATERIAL_TYPES.get(name);
    }
    public static List<MaterialType> getMaterialTypes() {
        return new ArrayList<>(MATERIAL_TYPES.values());
    }

//    public MaterialTypes() {
//        super("Material Types", 2);
//    }
//    @Override
//    public void consume(String name, JsonObject obj) {
//
//    }
}