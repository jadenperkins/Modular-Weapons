package com.main.content.loaders;

import com.main.constants.Icons;
import com.main.constants.PartTypes;
import com.main.content.Plugin;
import com.main.generate.weapon.WeaponPartType;
import com.main.pipeline.PipelineObjectPartType;

import java.awt.image.BufferedImage;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class PartTypeLoader extends ContentManager<PipelineObjectPartType> {
    public PartTypeLoader() {
        super("part_types", 1, Plugin::getPartTypes);
    }
    @Override
    public void consume(PipelineObjectPartType obj) {
        String iconName = obj.getIcon();
        BufferedImage icon = Icons.get(iconName);
        PartTypes.register(new WeaponPartType(obj.getName(), icon));
    }
}