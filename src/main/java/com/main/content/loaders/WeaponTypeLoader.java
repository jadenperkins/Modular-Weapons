package com.main.content.loaders;

import com.main.constants.PartTypes;
import com.main.constants.StatSets;
import com.main.constants.WeaponTypes;
import com.main.content.Plugin;
import com.main.generate.weapon.WeaponPartType;
import com.main.generate.weapon.WeaponType;
import com.main.pipeline.PipelineObjectWeaponType;
import com.main.scripts.ScriptWeapon;
import com.main.stat.StatSet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class WeaponTypeLoader extends ContentManager<PipelineObjectWeaponType> {
    public WeaponTypeLoader() {
        super("Weapon Types", 5, Plugin::getWeaponTypes);
    }
    @Override
    public void consume(PipelineObjectWeaponType object) {
        StatSet stats = StatSets.get(object.getStats());
        ScriptWeapon script = WeaponTypes.script(object.getScript());
        float weight = object.getWeight();
        List<String> partTypes = object.getParts();
        List<WeaponPartType> types = partTypes.stream().map(PartTypes::get).collect(Collectors.toList());
        WeaponPartType primary = object.getPrimary() != null ? PartTypes.get(object.getPrimary()) : types.get(0);

        WeaponType weapon = new WeaponType(object.getName(), weight, stats, primary, types, script);
        WeaponTypes.register(weapon);
    }
}