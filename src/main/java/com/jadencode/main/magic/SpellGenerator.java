package com.jadencode.main.magic;

import com.jadencode.main.generate.Generator;
import com.jadencode.main.util.WeightedRandomFloat;

import java.util.Random;

/**
 * Created by Jaden on 5/14/2015.
 */
public class SpellGenerator implements Generator<SpellObject> {

    @Override
    public SpellObject generate(Random r, int level) {
        SpellBase spellBase = WeightedRandomFloat.getRandomItem(r, SpellBase.getWeightedReferences()).theObject;
        return new SpellObject(spellBase, level, r);
    }

//    @Override
//    public void readNBT(NBTTagCompound nbt) {
//
//    }
//
//    @Override
//    public void writeNBT(NBTTagCompound nbt) {
//
//    }
//
//    @Override
//    public void onCreated(Random r) {
//
//    }
}
