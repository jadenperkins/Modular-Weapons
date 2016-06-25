package com.jadencode.main.content.loaders.scripts;

import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.scripts.ScriptItem;

/**
 * Created by JPERKI8 on 6/17/2016.
 */
public class ScriptWeaponLoader extends ScriptLoader<ScriptItem> {
    public ScriptWeaponLoader() {
        super("weapons", ScriptItem.class, ItemTypes.getScripts());
    }
}