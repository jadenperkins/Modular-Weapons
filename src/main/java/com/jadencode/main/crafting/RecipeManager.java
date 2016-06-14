package com.jadencode.main.crafting;

import com.jadencode.main.item.ItemPartBase;
import com.jadencode.main.item.ItemPartInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 6/16/2015.
 */
public class RecipeManager {
    private static final List<RecipeGeneric> recipeBases = new ArrayList<>();
    private static final List<Recipe>        recipes     = new ArrayList<>();

    public static final RecipeManager instance = new RecipeManager();

    public RecipeManager() {
//        newRecipe(ItemPartBase.ore, 1, ItemPartBase.ingot, 1);
//        GemLibrary lib = MaterialLibrary.getGemLibrary();
//        MaterialModified rough = lib.getResource(GemLibrary.diamond, GemLibrary.rough);
//        MaterialModified none = lib.getResource(GemLibrary.diamond, GemLibrary.none);
//        ItemPart partRough = ItemPart.getItemPart(ItemPartBase.jewel, rough);
//        ItemPart partNone = ItemPart.getItemPart(ItemPartBase.jewel, none);
//        newRecipe(new ItemPartInstance(partNone, 1, 1), new ItemPartInstance(partRough, 1, 1));
    }

    public static List<Recipe> getRecipes() {
        return recipes;
    }

    private void newRecipe(ItemPartInstance out, ItemPartInstance... in) {
        List<ItemPartInstance> input = new ArrayList<>();
        for (ItemPartInstance partInstance : in) {
            input.add(partInstance);
        }
        recipes.add(new Recipe(input, out));
    }
    private void newRecipe(ItemPartBase in, int i, ItemPartBase out, int o) {
        recipeBases.add(new RecipeGeneric(in, i, out, o));
    }
    public static void makeInstances() {
        for (RecipeGeneric recipeBase : recipeBases) {
            List<Recipe> r = recipeBase.createInstances();
            recipes.addAll(r);
        }
    }
}