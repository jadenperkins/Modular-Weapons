package com.jadencode.main.renderengine.particles;

import org.lwjgl.opengl.GL11;

/**
 * Created by gtrpl on 7/8/2016.
 */
public class ParticleTexture {
    private final int textureID;
    private final int numberOfRows;
    private final int blendingFunction;

    public ParticleTexture(int textureID, int numberOfRows, int blending) {
        this.textureID = textureID;
        this.numberOfRows = numberOfRows;
        this.blendingFunction = blending;
    }
    public ParticleTexture(int textureID, int numberOfRows) {
        this(textureID, numberOfRows, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    public int getTextureID() {
        return textureID;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public int getBlendingFunction() {
        return this.blendingFunction;
    }
}
