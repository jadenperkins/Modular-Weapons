package com.main.content.loaders;

import com.main.constants.Colors;
import com.main.constants.MaterialTypes;
import com.main.constants.Materials;
import com.main.generate.QualityLevel;
import com.main.material.Material;
import com.main.material.MaterialBase;
import com.main.material.MaterialType;
import com.main.pipeline.PipelineObjectMaterial;

import java.awt.*;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialLoader extends ContentManager<PipelineObjectMaterial> {
    public MaterialLoader() {
        super("materials", 7);
    }
    @Override
    public void consume(PipelineObjectMaterial object) {
        Color c = Colors.get(object.getColor());
        float w = object.getWeight();
        float m = object.getMod();
        int l = object.getLevel();
        MaterialType t = MaterialTypes.get(object.getMaterial());
        Material material = new MaterialBase(object.getName(), c, w, m, l, QualityLevel.COMMON, t);
        Materials.register(t, material);
    }
}