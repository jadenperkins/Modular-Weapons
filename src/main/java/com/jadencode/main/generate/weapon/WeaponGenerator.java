package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponParts;
import com.jadencode.main.constants.WeaponTypes;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class WeaponGenerator implements Generator<WeaponInstance> { //Generator<Sword> {
    @Override
    public WeaponInstance generate(Random r, int level) {
        WeaponType weaponType = WeightedRandomFloat.getRandomItem(r, WeaponTypes.getWeaponTypes());
        return this.generate(r, level, weaponType);
    }
    public WeaponInstance generate(Random r, int level, WeaponType weaponType) {
        List<WeaponPartInstance> partInstances = new ArrayList<>();

        for(WeaponPartType type : weaponType.getWeaponPartTypes()) {
            List<WeaponPart> parts = WeaponParts.getPartsList(type);
            WeaponPart part = WeightedRandomFloat.getRandomItem(r, parts);
            WeaponPartInstance instance = new WeaponPartInstance(part, level);
            partInstances.add(instance);
        }
        WeaponInstance weapon = new WeaponInstance(weaponType, partInstances);

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
