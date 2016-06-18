package com.jadencode.main.scripts;

import com.jadencode.main.generate.weapon.WeaponInstance;
import com.jadencode.main.generate.weapon.WeaponPartType;

import java.util.function.Function;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptWeapon extends ScriptBase {
    public ScriptWeapon(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public String getDisplayName(WeaponInstance instance) {
        Function<WeaponInstance, String> namingFunction = w -> {
            String s = "";
            for(WeaponPartType type : instance.getWeaponType().getWeaponPartTypes()) {
                s += w.getPart(type).getNameMod() + " ";
            }
            return s.trim();
        };
        return this.invokeWithDefault("getDisplayName", namingFunction.apply(instance));
    }
}