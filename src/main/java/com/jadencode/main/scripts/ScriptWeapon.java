package com.jadencode.main.scripts;

import com.jadencode.main.generate.item.ItemInstance;
import com.jadencode.main.generate.item.ItemPartType;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptWeapon extends ScriptBase {
    public ScriptWeapon(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public String getDisplayName(ItemInstance instance) {
        String s = instance.getPart(instance.getWeaponType().getPrimaryPartType()).getWeaponPart().getMaterialName();
        for (ItemPartType type : instance.getWeaponType().getWeaponPartTypes()) {
            s = s + " " + instance.getPart(type).getNameMod();
        }
        return this.invokeWithDefault("getDisplayName", s.trim(), instance);
    }
}