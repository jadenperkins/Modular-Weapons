package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by gtrpl on 7/2/2016.
 */
public class Renderer {

    private static final float fieldOfView = 70;
    private static final float nearPlane = 0.1F;
    private static final float farPlane = 1000F;
    private Matrix4f projectionMatrix;

    public Renderer(StaticShader shader) {
        this.createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(this.projectionMatrix);
        shader.stop();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1);
    }
    public void render(Entity entity, StaticShader shader) {
        TexturedModel texturedModel = entity.getModel();
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        Matrix4f transform = Maths.createTransformationMatrix(entity);
        shader.loadTransformationMatrix(transform);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float)((1F / Math.tan(Math.toRadians(fieldOfView / 2F))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;
        this.projectionMatrix = new Matrix4f();
        this.projectionMatrix.m00 = x_scale;
        this.projectionMatrix.m11 = y_scale;
        this.projectionMatrix.m22 = -((farPlane + nearPlane) / frustum_length);
        this.projectionMatrix.m23 = -1;
        this.projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustum_length);
        this.projectionMatrix.m33 = 0;
    }
}