package com.main.content.loaders;

import com.google.gson.JsonObject;
import com.main.constants.PartTypes;
import com.main.constants.StatSets;
import com.main.constants.WeaponTypes;
import com.main.generate.weapon.WeaponPartType;
import com.main.generate.weapon.WeaponType;
import com.main.pluginbuilder.JsonHelper;
import com.main.scripts.ScriptWeapon;
import com.main.stat.StatSet;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public class WeaponTypeLoader extends ContentManager {
    public WeaponTypeLoader() {
        super("Weapon Types", 5);
    }
    @Override
    public void consume(String name, JsonObject obj) {
        JsonHelper helper = new JsonHelper(obj);
        StatSet stats = StatSets.get(helper.getString("stats"));
        ScriptWeapon script = WeaponTypes.script(helper.getString("script"));
        float weight = helper.getFloat("weight", 1F);
        List<WeaponPartType> types = JsonHelper.fromArray(helper.getArray("parts")).stream().map(PartTypes::get).collect(Collectors.toList());
        WeaponPartType primary = obj.has("primary") ? PartTypes.get(helper.getString("primary")) : types.get(0);

        WeaponType weapon = new WeaponType(name, weight, stats, primary, types, script);
        WeaponTypes.register(weapon);
    }
}