package com.jadencode.main.constants;

import com.jadencode.main.material.MaterialModifier;
import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public class MaterialModifiers {
    private static final HashMap<MaterialType, List<MaterialModifier>> MODIFIERS = new HashMap<>();

    public static final MaterialModifier lunar = new MaterialModifier("Lunar", Colors.METAL_MOD_LUNAR, 1F, 0.5F, 1.2F, MaterialTypes.MATERIAL_METAL);//metal
    public static final MaterialModifier solar = new MaterialModifier("Solar", Colors.METAL_MOD_SOLAR, 1F, 0.5F, 0.8F, MaterialTypes.MATERIAL_METAL);//metal
    public static final MaterialModifier white = new MaterialModifier("White", Colors.METAL_MOD_WHITE, 1F, 0.8F, 1.2F, MaterialTypes.MATERIAL_METAL, MaterialTypes.MATERIAL_WOOD);//metal, wood
    public static final MaterialModifier black = new MaterialModifier("Black", Colors.METAL_MOD_BLACK, 1F, 0.8F, 0.8F, MaterialTypes.MATERIAL_METAL, MaterialTypes.MATERIAL_WOOD);//metal, wood
    public static final MaterialModifier ancient = new MaterialModifier("Ancient", Colors.METAL_MOD_ANCIENT, 1F, 1F, 1.2F, MaterialTypes.MATERIAL_METAL, MaterialTypes.MATERIAL_WOOD);//metal, wood
    public static final MaterialModifier shadow = new MaterialModifier("Shadow", Colors.METAL_MOD_SHADOW, 1F, 1F, 0.8F, MaterialTypes.MATERIAL_METAL);//metal
    public static final MaterialModifier dark = new MaterialModifier("Dark", Colors.METAL_MOD_DARK, 1F, 1.25F, 1.2F, MaterialTypes.MATERIAL_METAL, MaterialTypes.MATERIAL_WOOD, MaterialTypes.MATERIAL_GEM);//metal, wood
    public static final MaterialModifier twilight = new MaterialModifier("Twilight", Colors.METAL_MOD_TWILIGHT, 1F, 1.25F, 0.8F, MaterialTypes.MATERIAL_METAL);//metal
    public static final MaterialModifier ghost = new MaterialModifier("Ghost", Colors.METAL_MOD_GHOST, 1F, 1.5F, 2F, MaterialTypes.MATERIAL_METAL);//metal

    public static final MaterialModifier red = new MaterialModifier("Red", Colors.WOOD_MOD_RED, 1F, 0.8F, 1.2F, MaterialTypes.MATERIAL_WOOD);//wood
    public static final MaterialModifier light = new MaterialModifier("Light", Colors.WOOD_MOD_LIGHT, 1F, 1F, 1.2F, MaterialTypes.MATERIAL_WOOD);//wood
    public static final MaterialModifier hardy = new MaterialModifier("Hardy", Colors.WOOD_MOD_HARDY, 1F, 1.25F, 0.8F, MaterialTypes.MATERIAL_WOOD);//wood
    public static final MaterialModifier polar = new MaterialModifier("Polar", Colors.WOOD_MOD_POLAR, 1F, 1.5F, 2F, MaterialTypes.MATERIAL_WOOD, MaterialTypes.MATERIAL_PLANT);//wood, plant

    //Plants only
    public static final MaterialModifier young = new MaterialModifier("Young", Colors.PLANT_MOD_YOUNG, 1F, 0.5F, 0.8F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier withered = new MaterialModifier("Withered", Colors.PLANT_MOD_WITHERED, 1F, 0.5F, 0.3F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier creeping = new MaterialModifier("Creeping", Colors.PLANT_MOD_CREEPING, 1F, 1.5F, 1.5F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier wild = new MaterialModifier("Wild", Colors.PLANT_MOD_WILD, 1F, 1.5F, 1F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier mature = new MaterialModifier("Mature", Colors.PLANT_MOD_MATURE, 1F, 0.8F, 1.2F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier extinct = new MaterialModifier("Extinct", Colors.PLANT_MOD_EXTINCT, 1F, 1.8F, 2F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier juvenile = new MaterialModifier("Juvenile", Colors.PLANT_MOD_JUVENILE, 1F, 0.5F, 0.8F, MaterialTypes.MATERIAL_PLANT);
    public static final MaterialModifier leafy = new MaterialModifier("Leafy", Colors.PLANT_MOD_LEAFY, 1F, 1.8F, 2F, MaterialTypes.MATERIAL_PLANT);

    public static final MaterialModifier rough = new MaterialModifier("Rough", Colors.GEM_MOD_ROUGH, 1F, 0.8F, 1.2F, MaterialTypes.MATERIAL_GEM);
    public static final MaterialModifier vibrant = new MaterialModifier("Vibrant", Colors.GEM_MOD_VIBRANT, 1F, 1.5F, 2F, MaterialTypes.MATERIAL_GEM);
    public static final MaterialModifier faded = new MaterialModifier("Faded", Colors.GEM_MOD_FADED, 1F, 0.5F, 1.2F, MaterialTypes.MATERIAL_GEM);
    public static final MaterialModifier dull = new MaterialModifier("Dull", Colors.GEM_MOD_DULL, 1F, 0.8F, 0.8F, MaterialTypes.MATERIAL_GEM);


    public static void register(MaterialType type, MaterialModifier modifier) {
        if(!MODIFIERS.containsKey(type)) MODIFIERS.put(type, new ArrayList<>());
        MODIFIERS.get(type).add(modifier);
    }
    public static List<MaterialModifier> getModifiers(MaterialType type) {
        return MODIFIERS.getOrDefault(type, new ArrayList<>());
    }
}
