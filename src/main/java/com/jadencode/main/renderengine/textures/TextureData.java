package com.jadencode.main.renderengine.textures;

import java.nio.ByteBuffer;

/**
 * Created by gtrpl on 7/6/2016.
 */
public class TextureData {
    private final int width;
    private final int height;
    private final ByteBuffer buffer;

    public TextureData(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public ByteBuffer getBuffer() {
        return buffer;
    }
}
