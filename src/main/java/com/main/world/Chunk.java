package com.main.world;

/**
 * Created by Jaden on 1/25/2015.
 */
public class Chunk {

    public static final int CHUNK_SIZE = 16;

    private int chunkX;
    private int chunkY;
    private int chunkZ;
    private Block[] blocks = new Block[CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE];

    public Chunk(int x, int y, int z) {
        this.chunkX = x;
        this.chunkY = y;
        this.chunkZ = z;
    }
    public void setBlock(int i, int j, int k, Block block) {
        int index = i * CHUNK_SIZE * CHUNK_SIZE + j * CHUNK_SIZE + k;
        System.out.println(String.format("%d, %d, %d, %d", i, j, k, index));
        this.blocks[index] = block;
    }
    public Block getBlock(int i, int j, int k) {
        int index = i * CHUNK_SIZE * CHUNK_SIZE + j * CHUNK_SIZE + k;
        return this.blocks[index];
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public int getChunkZ() {
        return chunkZ;
    }
}