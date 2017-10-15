package com.main.plugins.loaders;

import com.main.constants.MaterialTypes;
import com.main.material.MaterialType;
import com.main.plugins.Plugin;
import com.main.plugins.pipeline.PipelineObjectMaterialType;
import com.main.scripts.ScriptMaterialType;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialTypeLoader extends ContentManager<PipelineObjectMaterialType> {
    public MaterialTypeLoader() {
        super("material_types", 0, Plugin::getMaterialTypes);
    }
    @Override
    public void consume(PipelineObjectMaterialType object) {
        String scriptName = object.getScript();
        ScriptMaterialType script = MaterialTypes.script(scriptName);
        MaterialTypes.register(new MaterialType(object.getName(), script));
    }
}