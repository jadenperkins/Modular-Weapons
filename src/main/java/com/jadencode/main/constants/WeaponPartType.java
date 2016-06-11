package com.jadencode.main.constants;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class WeaponPartType {
    public static final WeaponPartType PART_SWORD_HILT  = new WeaponPartType("sword_hilt");
    public static final WeaponPartType PART_SWORD_GRIP  = new WeaponPartType("sword_grip");
    public static final WeaponPartType PART_SWORD_BLADE = new WeaponPartType("sword_blade");

    private final String typeName;

    public WeaponPartType(String s) {
        this.typeName = s;
    }
    public String getTypeName() {
        return typeName;
    }
}