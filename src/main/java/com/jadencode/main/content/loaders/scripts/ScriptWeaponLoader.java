package com.jadencode.main.content.loaders.scripts;

import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.scripts.ScriptWeapon;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptWeaponLoader extends ScriptLoader<ScriptWeapon> {
    public ScriptWeaponLoader() {
        super("weapons", ScriptWeapon.class, ItemTypes.getScripts());
    }
}