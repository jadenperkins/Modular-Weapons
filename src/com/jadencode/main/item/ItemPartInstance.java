package com.upadvisor.main.item;

/**
 * Created by Jaden on 6/16/2015.
 */
public class ItemPartInstance {
    private final int level;
    private int stackSize;
    private final ItemPart basePart;

    public ItemPartInstance(ItemPart part, int level, int stack) {
        this.basePart = part;
        this.level = level;
        this.stackSize = stack;
    }

    public ItemPart getBasePart() {
        return basePart;
    }

    public int getLevel() {
        return level;
    }
    public int getStackSize() {
        return this.stackSize;
    }
    public String getPartInfo() {
        return String.format("%s (Size: %d)", this.getBasePart().getPartInfo(), this.getStackSize());
    }
}