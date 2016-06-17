package com.jadencode.main.content.scripts.loaders;

import com.jadencode.main.constants.WeaponTypes;
import com.jadencode.main.content.scripts.ScriptWeapon;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptWeaponLoader extends ScriptLoader<ScriptWeapon> {
    public ScriptWeaponLoader() {
        super("weapons", ScriptWeapon.class, WeaponTypes.getScripts());
    }
}