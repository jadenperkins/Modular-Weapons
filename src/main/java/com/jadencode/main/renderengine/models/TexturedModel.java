package com.jadencode.main.renderengine.models;

import com.jadencode.main.renderengine.textures.ModelTexture;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class TexturedModel {
    private final RawModel rawModel;
    private final ModelTexture texture;
    public TexturedModel(RawModel rawModel, ModelTexture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }
    public RawModel getRawModel() {
        return rawModel;
    }
    public ModelTexture getTexture() {
        return texture;
    }
}