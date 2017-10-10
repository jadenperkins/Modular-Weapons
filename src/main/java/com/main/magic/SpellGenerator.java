package com.main.magic;

import com.main.generate.Generator;
import com.main.nbt.NBTTagCompound;
import com.main.util.WeightedRandomFloat;

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
