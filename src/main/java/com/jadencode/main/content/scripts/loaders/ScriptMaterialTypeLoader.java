package com.jadencode.main.content.scripts.loaders;

import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.content.scripts.ScriptMaterialType;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptMaterialTypeLoader extends ScriptLoader<ScriptMaterialType> {
    public ScriptMaterialTypeLoader() {
        super("materials/types", ScriptMaterialType.class, MaterialTypes.getScripts());
    }
}