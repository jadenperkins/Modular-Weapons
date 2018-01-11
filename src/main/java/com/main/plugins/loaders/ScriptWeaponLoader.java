package com.main.plugins.loaders;

import com.main.constants.WeaponTypes;
import com.main.scripts.ScriptWeapon;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptWeaponLoader extends ScriptLoader<ScriptWeapon> {
    public ScriptWeaponLoader() {
        super("weapons", ScriptWeapon.class, WeaponTypes.getScripts());
    }
}