package com.jadencode.main.constants;

import com.jadencode.main.generate.QualityLevel;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialModified;
import com.jadencode.main.material.MaterialModifier;
import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class Materials {
    private static final HashMap<MaterialType, List<Material>> MATERIALS = new HashMap<>();

    public static void register(MaterialType type, Material material) {
        if(!MATERIALS.containsKey(type)) MATERIALS.put(type, new ArrayList<>());
        MATERIALS.get(type).add(material);
    }
    public static List<Material> getMaterials(MaterialType type) {
        return MATERIALS.getOrDefault(type, new ArrayList<>());
    }

    public static void load() {
        for (MaterialType type : MaterialTypes.getMaterialTypes()) {
            List<Material> commonMaterials = new ArrayList<>(getMaterials(type));
            List<Material> exoticMaterials = type.generateExotics();

            List<MaterialModifier> modifiers = MaterialModifiers.getModifiers(type);
            for (MaterialModifier modifier : modifiers) {
                for (Material commonMaterial : commonMaterials) {
                    Material m = new MaterialModified(commonMaterial, QualityLevel.UNCOMMON, modifier);
                    Materials.register(type, m);
                }
                for (Material exoticMaterial : exoticMaterials) {
                    Material m = new MaterialModified(exoticMaterial, QualityLevel.EPIC, modifier);
                    Materials.register(type, m);
                }
            }

//            for (Material material : baseMaterials) {
//                for (MaterialModifier modifier : MaterialModifiers.getModifiers(type)) {
//                    Material m = new MaterialModified(material, modifier);
//                    Materials.register(type, m);
//                }
//            }
            System.out.println(String.format("Registered %d %s materials!", getMaterials(type).size(), type.getName()));
        }
    }
}