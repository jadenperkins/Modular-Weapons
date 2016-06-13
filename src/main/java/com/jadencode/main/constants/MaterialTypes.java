package com.jadencode.main.constants;

import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class MaterialTypes {

    private static List<MaterialType> MATERIAL_TYPES = new ArrayList<>();

    public static final MaterialType MATERIAL_METAL = new MaterialType("Metal");
    public static final MaterialType MATERIAL_WOOD = new MaterialType("Wood");
    public static final MaterialType MATERIAL_PLANT = new MaterialType("Plant");
    public static final MaterialType MATERIAL_GEM = new MaterialType("Gem");

    public static void register(MaterialType type) {
        MATERIAL_TYPES.add(type);
    }
    public static List<MaterialType> getMaterialTypes() {
        return MATERIAL_TYPES;
    }
}