package com.jadencode.main.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    int COLORS = 0;
    int STATS = 1;
    int MATERIAL_TYPES = 2;
    int PART_TYPES = 3;
    int STAT_SETS = 4;
    int MATERIAL_MODIFIERS = 5;
    int MATERIALS = 6;
    int WEAPON_TYPES = 7;
    int WEAPON_PARTS = 8;

    int type();
}