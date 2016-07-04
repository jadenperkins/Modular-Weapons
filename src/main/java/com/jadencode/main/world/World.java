package com.jadencode.main.world;

import java.util.HashMap;

/**
 * Created by Jaden on 1/25/2015.
 */
public class World {
    private HashMap<Integer, HashMap<Integer, HashMap<Integer, Chunk>>> chunkMap = new HashMap<>();

    public World() {

    }

    public void addChunk(int x, int y, int z) {
        Chunk chunk = new Chunk(x, y, z);
        this.addChunk(chunk);
    }

    public Chunk getChunk(int x, int y, int z) {
        HashMap<Integer, HashMap<Integer, Chunk>> yMap = chunkMap.get(x);
        if (yMap == null) {
            return null;
        }
        HashMap<Integer, Chunk> zMap = yMap.get(y);
        if (zMap == null) {
            return null;
        }
        Chunk chunk = zMap.get(z);
        if (chunk == null) {
            return null;
        }
        return chunk;
    }

    public Block getBlock(int i, int j, int k) {
        int x = (int) Math.floor((double) i / Chunk.CHUNK_SIZE);
        int y = (int) Math.floor((double) j / Chunk.CHUNK_SIZE);
        int z = (int) Math.floor((double) k / Chunk.CHUNK_SIZE);

        Chunk chunk = this.getChunk(x, y, z);
        if (chunk == null) {
            return Block.air;
        }
        return chunk.getBlock(Math.abs(i % Chunk.CHUNK_SIZE), Math.abs(j % Chunk.CHUNK_SIZE), Math.abs(k % Chunk.CHUNK_SIZE));
    }

    public void addChunk(Chunk chunk) {
        HashMap<Integer, Chunk> zMap = new HashMap<>();
        zMap.put(chunk.getChunkZ(), chunk);
        HashMap<Integer, HashMap<Integer, Chunk>> yMap = new HashMap<>();
        yMap.put(chunk.getChunkY(), zMap);
        this.chunkMap.put(chunk.getChunkX(), yMap);
    }

    public void setBlock(int i, int j, int k, Block block) {
        int x = (int) Math.floor((double) i / Chunk.CHUNK_SIZE);
        int y = (int) Math.floor((double) j / Chunk.CHUNK_SIZE);
        int z = (int) Math.floor((double) k / Chunk.CHUNK_SIZE);

        System.out.println(String.format("Chunk coords: %d, %d, %d", x, y, z));
        Chunk chunk = this.getChunk(x, y, z);
        if (chunk == null) {
            chunk = new Chunk(x, y, z);
            this.addChunk(chunk);
        }
        chunk.setBlock(Math.abs(i % Chunk.CHUNK_SIZE), Math.abs(j % Chunk.CHUNK_SIZE), Math.abs(k % Chunk.CHUNK_SIZE), block);
    }
}
