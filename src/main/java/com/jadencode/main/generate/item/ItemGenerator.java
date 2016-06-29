package com.jadencode.main.generate.item;

import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.generate.item.type.ItemType;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class ItemGenerator implements Generator<Item> { //Generator<Sword> {
    @Override
    public Item generate(Random r, int level) {
//        List<ItemType> types;
//        int check = r.nextInt(4);
//        if(check == 0) {
//            types = ItemTypes.getModularTypes();
//        }
//        else if(check == 1) {
//            types = ItemTypes.getUniqueTypes();
//        }
//        else if(check == 2) {
//            types = ItemTypes.getMaterializedTypes();
//        }
//        else {
//            types = ItemTypes.getItemTypes();
//        }
//        if(types.isEmpty()) types = ItemTypes.getItemTypes();
        List<ItemType> types = ItemTypes.getModularTypes();
        ItemType itemType = WeightedRandomFloat.getRandomItem(r, types);
        return this.generate(r, level, itemType);
    }
    public Item generate(Random r, int level, ItemType itemType) {
        return itemType.create(r, level);
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
