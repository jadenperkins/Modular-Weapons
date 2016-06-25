package com.jadencode.main.scripts;

import com.jadencode.main.generate.item.instance.Item;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptItem extends ScriptBase {
    public ScriptItem(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public String getDisplayName(Item instance, String fallback) {
//        String s = instance.getPart(instance.getWeaponType().getPrimaryPartType()).getWeaponPart().getMaterialName();
//        for (ItemPartType type : instance.getWeaponType().getItemPartTypes()) {
//            s = s + " " + instance.getPart(type).getNameMod();
//        }
        return this.invokeWithDefault("getDisplayName", fallback, instance);
    }
}