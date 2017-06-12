package com.jadencode.main.content.loaders.scripts;

import com.jadencode.main.constants.MaterialTypes;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.scripts.ScriptMaterialType;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptMaterialTypeLoader extends ScriptLoader<ScriptMaterialType> {

    public ScriptMaterialTypeLoader() {
        super(Strings.Loaders.SCRIPT_MATERIAL_TYPES, ScriptMaterialType.class, MaterialTypes.getScripts());
    }
}