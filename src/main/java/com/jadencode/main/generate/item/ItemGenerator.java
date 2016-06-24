package com.jadencode.main.generate.item;

import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class ItemGenerator implements Generator<ItemInstance> { //Generator<Sword> {
    @Override
    public ItemInstance generate(Random r, int level) {
        ItemType weaponType = WeightedRandomFloat.getRandomItem(r, ItemTypes.getWeaponTypes());
        return this.generate(r, level, weaponType);
    }
    public ItemInstance generate(Random r, int level, ItemType weaponType) {
        List<ItemPartInstance> partInstances = new ArrayList<>();

        for(ItemPartType type : weaponType.getWeaponPartTypes()) {
            List<ItemPart> parts = ItemParts.getPartsList(type);
            ItemPart part = WeightedRandomFloat.getRandomItem(r, parts);
            ItemPartInstance instance = new ItemPartInstance(part, level);
            partInstances.add(instance);
        }
        ItemInstance weapon = new ItemInstance(weaponType, partInstances);

        return weapon;
    }
//    @Override
//    public void readNBT(NBTTagCompound nbt) {
//
//    }
//    @Override
//    public void writeNBT(NBTTagCompound nbt) {
//
//    }
//    @Override
//    public void onCreated(Random r) {
//
//    }
}
