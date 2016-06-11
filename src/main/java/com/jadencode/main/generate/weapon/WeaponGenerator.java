package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponPartType;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.util.WeaponParts;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class WeaponGenerator implements Generator<WeaponInstance> { //Generator<Sword> {
    @Override
    public WeaponInstance generate(Random r, int level) {
        WeaponClass weaponClass = WeightedRandomFloat.getRandomWeightable(r, WeaponClass.getWeaponClasses());
        return this.generate(r, level, weaponClass);
    }
    public WeaponInstance generate(Random r, int level, WeaponClass weaponClass) {
        HashMap<WeaponPartType, WeaponPartInstance> partInstances = new HashMap<>();

        for(WeaponPartType type : weaponClass.getWeaponPartTypes()) {
            List<WeaponPart> parts = WeaponParts.getPartsList(type);
            WeaponPart part = WeightedRandomFloat.getRandomWeightable(r, parts);
            WeaponPartInstance instance = new WeaponPartInstance(part, level);
            partInstances.put(type, instance);
        }
        WeaponInstance weapon = new WeaponInstance(weaponClass, partInstances);

        return weapon;
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void onCreated(Random r) {

    }
}
