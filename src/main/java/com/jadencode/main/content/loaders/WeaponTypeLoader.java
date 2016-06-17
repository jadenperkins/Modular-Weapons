package com.jadencode.main.content.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.PartTypes;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.constants.WeaponTypes;
import com.jadencode.main.generate.weapon.WeaponInstance;
import com.jadencode.main.generate.weapon.WeaponPartInstance;
import com.jadencode.main.generate.weapon.WeaponPartType;
import com.jadencode.main.generate.weapon.WeaponType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
        float weight = obj.has("weight") ? obj.get("weight").getAsFloat() : 1F;
        JsonArray partsArray = obj.get("parts").getAsJsonArray();
        List<String> parts = new ArrayList<>();
        partsArray.forEach(element -> parts.add(element.getAsString()));
        List<WeaponPartType> partTypes = parts.stream().map(PartTypes::get).collect(Collectors.toList());
        Function<WeaponInstance, String> namingFunction = w -> {
            String s = "";
            for(WeaponPartType type : partTypes) {
                s += w.getPart(type).getNameMod() + " ";
            }
            return s.trim();
        };
        WeaponType weapon = new WeaponType(name, weight, StatSets.get(statSetName), partTypes, namingFunction);
        WeaponTypes.register(weapon);
    }
}