package com.jadencode.main.constants;

import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class MaterialTypes {

    private static final List<String> M1 = Arrays.asList("Meg", "Ex", "Ant", "Mas", "Ox", "Ax", "Bis");
    private static final List<String> M2 = Arrays.asList("or", "an", "net", "et", "en");
    private static final List<String> M3 = Arrays.asList("ite", "ium");

    private static final List<String> W1 = Arrays.asList("Ac", "Za", "Mat", "Ni");
    private static final List<String> W2 = Arrays.asList("tan", "rop", "cat", "mat");
    private static final List<String> W3 = Arrays.asList("ta", "in", "or", "za");

    private static final List<String> P1 = Arrays.asList("Snake", "Blood", "Gleam", "String", "Witch");
    private static final List<String> P2 = Arrays.asList("thorn", "root", "leaf", "stem", "blade", "grass",
            "bell", "flower", "bloom", "blossom");

    private static final List<String> G1 = Arrays.asList("Blood", "Dragon", "Shadow", "Sky", "Thunder");
    private static final List<String> G2 = Arrays.asList("stone", "rock", "gem", "spar", "quartz", "glass", "jewel");

    private static final List<MaterialType> MATERIAL_TYPES = new ArrayList<>();

    public static final MaterialType MATERIAL_METAL = new MaterialType("Metal", supplier(M1, M2, M3));
    public static final MaterialType MATERIAL_WOOD = new MaterialType("Wood", supplier(W1, W2, W3));
    public static final MaterialType MATERIAL_PLANT = new MaterialType("Plant", supplier(P1, P2));
    public static final MaterialType MATERIAL_GEM = new MaterialType("Gem", supplier(G1, G2));

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
        MATERIAL_TYPES.add(type);
    }
    public static List<MaterialType> getMaterialTypes() {
        return MATERIAL_TYPES;
    }
}