package com.main.plugins.loaders;

import com.main.constants.MaterialTypes;
import com.main.scripts.ScriptMaterialType;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptMaterialTypeLoader extends ScriptLoader<ScriptMaterialType> {
    public ScriptMaterialTypeLoader() {
        super("material types", ScriptMaterialType.class, MaterialTypes.getScripts());
    }
}