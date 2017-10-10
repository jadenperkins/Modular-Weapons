package com.main.crafting;

import com.main.item.ItemPart;
import com.main.item.ItemPartBase;
import com.main.material.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 6/16/2015.
 */
public class RecipeGeneric {
    private final ItemPartBase input;
    private final ItemPartBase output;
    private final int inCount;
    private final int outCount;

    public RecipeGeneric(ItemPartBase in, int inc, ItemPartBase out, int outc) {
        this.input = in;
        this.inCount = inc;
        this.output = out;
        this.outCount = outc;
    }
    public List<Recipe> createInstances() {
        List<Recipe> ret = new ArrayList<>();

        //Get all ItemPart that use the input as its ItemPartBase
        List<ItemPart> ins = ItemPart.getItemParts(this.input);
        //If the list exists
        if(ins != null) {
            //Iterate through each ItemPart
            for(ItemPart in : ins) {
                //Get the base material for the ItemPart
                Material res = in.getBaseResource();
                //Get an ItemPart consume the output ItemPartBase and the input's resource
//                ItemPart out = ItemPart.getItemPart(this.output, res);
                //Create a Recipe instance of this RecipeBase using the input and output, level 1
//                Recipe r = new Recipe(Arrays.asList(new ItemPartInstance(in, 1, inCount)), new ItemPartInstance(out, 1, outCount));
                //Add the instance to the list
//                ret.add(r);
            }
        }
        //return the list
        return ret;
    }
}