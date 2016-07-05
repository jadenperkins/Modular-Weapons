package com.jadencode.main.renderengine.entities;

import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.toolbox.Transform;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class Entity implements Transform {
    private final TexturedModel model;
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;
    private final int textureIndex;

    public Entity(TexturedModel model, Vector3f translation, Vector3f rotation, Vector3f scale) {
        this(model, translation, rotation, scale, 0);
    }

    public Entity(TexturedModel model, Vector3f translation, Vector3f rotation, Vector3f scale, int textureIndex) {
        this.model = model;
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
        this.textureIndex = textureIndex;
    }
    public Vector2f getTextureOffset() {
        int col = textureIndex % model.getTexture().getNumberOfRows();
        int row = textureIndex / model.getTexture().getNumberOfRows();
        return new Vector2f((float) col / model.getTexture().getNumberOfRows(), (float) row / model.getTexture().getNumberOfRows());
    }
    public void translate(float x, float y, float z) {
        this.translation.x += x;
        this.translation.y += y;
        this.translation.z += z;
    }

    public void setPosition(float x, float y, float z) {
        this.translation.x = x;
        this.translation.y = y;
        this.translation.z = z;
    }

    public void rotate(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public void scale(float x, float y, float z) {

    }

    public TexturedModel getModel() {
        return model;
    }

    @Override
    public Vector3f getTranslation() {
        return translation;
    }

    @Override
    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public Vector3f getScale() {
        return scale;
    }

    public float getYRot() {
        return rotation.getY();
    }

}