package com.main.content.loaders;

import com.main.constants.Colors;
import com.main.constants.MaterialModifiers;
import com.main.constants.MaterialTypes;
import com.main.material.MaterialModifier;
import com.main.material.MaterialType;
import com.main.pipeline.PipelineObjectMaterialModifier;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class MaterialModifierLoader extends ContentManager<PipelineObjectMaterialModifier> {
    public MaterialModifierLoader() {
        super("MaterialBase Modifiers", 6);
    }
    @Override
    public void consume(PipelineObjectMaterialModifier object) {
        Color c = Colors.get(object.getColor());
        float w = object.getWeight();
        float l = object.getLevel();
        float m = object.getMod();
        List<MaterialType> materialTypes = object.getMaterials().stream().map(MaterialTypes::get).collect(Collectors.toList());
        MaterialModifier modifier = new MaterialModifier(object.getName(), c, w, m, l, materialTypes);
        materialTypes.forEach(type -> MaterialModifiers.register(type, modifier));
    }
}