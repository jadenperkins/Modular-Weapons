package com.jadencode.main.generate.item;

import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.generate.item.type.ItemType;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.List;
import java.util.Random;

/**
 * Created by Jaden on 1/22/2015.
 */
public class ItemGenerator implements Generator<Item> {
    @Override
    public Item generate(Random r, int level) {
        List<ItemType> types = ItemTypes.getModularTypes();
        ItemType itemType = WeightedRandomFloat.getRandomItem(r, types);
        return this.generate(r, level, itemType);
    }
    public Item generate(Random r, int level, ItemType itemType) {
        return itemType.create(r, level);
    }
}
