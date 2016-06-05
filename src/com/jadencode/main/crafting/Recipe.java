package com.upadvisor.main.crafting;

import com.upadvisor.main.item.ItemPart;
import com.upadvisor.main.item.ItemPartInstance;

import java.util.List;

/**
 * Created by Jaden on 6/16/2015.
 */
public class Recipe {
    private final ItemPartInstance output;
    private final List<ItemPartInstance> inputs;

    public Recipe(List<ItemPartInstance> in, ItemPartInstance out) {
        this.output = out;
        this.inputs = in;
    }
    public List<ItemPartInstance> getInput() {
        return inputs;
    }

    public ItemPartInstance getOutput() {
        return output;
    }
}
