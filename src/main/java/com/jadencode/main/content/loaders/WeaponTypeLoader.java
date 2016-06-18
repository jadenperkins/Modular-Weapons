package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.PartTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.WeaponTypes;
import com.jadencode.main.generate.weapon.WeaponPartType;
import com.jadencode.main.generate.weapon.WeaponType;
import com.jadencode.main.scripts.ScriptWeapon;

import java.util.ArrayList;
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
        String statSetName = obj.get("stats").getAsString();
        String scriptName = obj.has("script") ? obj.get("script").getAsString() : null;
        ScriptWeapon script = WeaponTypes.script(scriptName);
        float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        JsonArray partsArray = obj.get("parts").getAsJsonArray();
        List<String> parts = new ArrayList<>();
        partsArray.forEach(element -> parts.add(element.getAsString()));
        List<WeaponPartType> partTypes = parts.stream().map(PartTypes::get).collect(Collectors.toList());
        WeaponType weapon = new WeaponType(name, weight, StatSets.get(statSetName), partTypes, script);
        WeaponTypes.register(weapon);
    }
}