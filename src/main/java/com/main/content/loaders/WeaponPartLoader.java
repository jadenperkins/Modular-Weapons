package com.main.content.loaders;

import com.main.constants.*;
import com.main.content.Plugin;
import com.main.generate.weapon.WeaponPartBase;
import com.main.generate.weapon.WeaponPartLegendary;
import com.main.generate.weapon.WeaponPartType;
import com.main.material.MaterialType;
import com.main.pipeline.PipelineObjectWeaponPart;
import com.main.stat.StatSet;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class WeaponPartLoader extends ContentManager<PipelineObjectWeaponPart> {
    public WeaponPartLoader() {
        super("weapon_parts", 8, Plugin::getWeaponParts);
    }
    @Override
    public void consume(PipelineObjectWeaponPart obj) {
        boolean isLegendary = obj.getMaterials().isEmpty();
        String nameMod = obj.getNameMod();
        String partInfo = obj.getPartInfo();
        String stats = obj.getStats();
        StatSet set = stats == null ? StatSets.EMPTY : StatSets.get(stats);
        float weight = obj.getWeight();
        String iconName = obj.getIcon();
        BufferedImage icon = Icons.get(iconName);
        WeaponPartType type = PartTypes.get(obj.getPartType());

        if(isLegendary) {
            WeaponParts.register(new WeaponPartLegendary(obj.getName(), nameMod, partInfo, set, weight, icon, type));
        } else {
            List<String> materials = obj.getMaterials();
            List<MaterialType> types = materials.stream().map(MaterialTypes::get).collect(Collectors.toList());
            WeaponParts.addBasePart(new WeaponPartBase(obj.getName(), nameMod, weight, set, type, icon, types));
        }
    }
}