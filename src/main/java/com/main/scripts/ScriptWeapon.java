package com.main.scripts;

import com.main.generate.weapon.WeaponInstance;
import com.main.generate.weapon.WeaponPartType;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptWeapon extends ScriptBase {
    public ScriptWeapon(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public String getDisplayName(WeaponInstance instance) {
        String s = instance.getPart(instance.getWeaponType().getPrimaryPartType()).getWeaponPart().getMaterialName();
        for (WeaponPartType type : instance.getWeaponType().getWeaponPartTypes()) {
            s = s + " " + instance.getPart(type).getNameMod();
        }
        return this.invokeWithDefault("getDisplayName", s.trim(), instance);
    }
}