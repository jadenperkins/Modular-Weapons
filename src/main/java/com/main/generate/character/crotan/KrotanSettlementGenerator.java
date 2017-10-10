package com.main.generate.character.crotan;

import com.main.generate.Generator;
import com.main.generate.character.Settlement;
import com.main.nbt.NBTTagCompound;

import java.util.Random;

/**
 * Created by Jaden on 7/20/2015.
 */
public class KrotanSettlementGenerator implements Generator<Settlement> {
    @Override
    public Settlement generate(Random r, int level) {
        return null;
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void readNBT(NBTTagCompound nbt) {

    }

    @Override
    public void onCreated(Random r) {

    }
}