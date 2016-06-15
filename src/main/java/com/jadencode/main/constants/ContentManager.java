package com.jadencode.main.constants;

/**
 * Created by JPERKI8 on 6/15/2016.
 */
public class ContentManager {

    public static final Colors            COLORS             = new Colors();
    public static final Stats             STATS              = new Stats();
    public static final StatSets          STAT_SETS          = new StatSets();
    public static final MaterialTypes     MATERIAL_TYPES     = new MaterialTypes();
    public static final MaterialModifiers MATERIAL_MODIFIERS = new MaterialModifiers();
    public static final Materials         MATERIALS          = new Materials();
    public static final PartTypes         PART_TYPES         = new PartTypes();
    public static final WeaponTypes       WEAPON_TYPES       = new WeaponTypes();
    public static final WeaponParts       WEAPON_PARTS       = new WeaponParts();

    public static final void load() {
//        COLORS.load();
//        MATERIAL_TYPES.load();
//        STATS.load();
//        PART_TYPES.load();
//        MATERIAL_MODIFIERS.load();
//        MATERIALS.load();
//        STAT_SETS.load();
//        WEAPON_TYPES.load();
//        WEAPON_PARTS.load();

        //        Colors                None
        //        MaterialTypes         None
        //        Stats                 None
        //        PartTypes             None

        //        MaterialModifiers     Color, MaterialType
        //        Materials             Color, MaterialType, MaterialModifier
        //        Materials.load();     MaterialType, Material, MaterialModifier


        //        StatSets              Stats
        //        WeaponTypes           StatSet, PartType
        //        WeaponParts           StatSet, PartType, MaterialType
        //        WeaponParts.generateWeaponParts();    WeaponParts, Material
    }
}