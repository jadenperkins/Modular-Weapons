package com.jadencode.main.world;

import java.util.HashMap;

/**
 * Created by Jaden on 1/25/2015.
 */
public class Block {
    public static final Block air = new Block("Air");
    public static final Block stone = new Block("Stone");
    public static final Block dirt = new Block("Dirt");
    public static final Block log = new Block("Log");
    private static final HashMap<String, Block> blockMap = new HashMap<>();
    private final String blockID;

    public Block(String id) {
        if (blockMap.get(id) == null) {
            this.blockID = id;
            blockMap.put(id, this);
        } else {
            throw new IllegalArgumentException(String.format("%s already in use!", id));
        }
    }

    public static Block getBlock(String id) {
        return blockMap.get(id);
    }

    public String getBlockID() {
        return this.blockID;
    }
}
