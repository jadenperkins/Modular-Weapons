package com.jadencode.main.renderengine.textures;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class ModelTexture {
    private final int textureID;
    private float shineDamper = 1;
    private float reflectivity = 0;

    public ModelTexture(int id) {
        this.textureID = id;
    }
    public int getTextureID() {
        return textureID;
    }
    public float getShineDamper() {
        return shineDamper;
    }
    public float getReflectivity() {
        return reflectivity;
    }
    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }
    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}