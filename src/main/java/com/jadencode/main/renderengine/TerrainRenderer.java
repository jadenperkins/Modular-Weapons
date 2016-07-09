package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.terrain.Terrain;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;

/**
 * Created by gtrpl on 7/2/2016.
 */
public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.PROJECTION_MATRIX.load(projectionMatrix);
        shader.connectTextureUnits();
        shader.stop();
    }

    public void render(List<Terrain> terrains, Matrix4f toShadowSpace) {
        shader.SHADOW_MAP_SPACE.load(toShadowSpace);
        for (Terrain terrain : terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }

    private void prepareTerrain(Terrain terrain) {
        RawModel model = terrain.getModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        terrain.bindTextures();
        this.shader.SHINE_DAMPER.load(1F);
        this.shader.REFLECTIVITY.load(0F);
    }
    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transform = Maths.createTransformationMatrix(terrain);
        this.shader.TRANSFORMATION_MATRIX.load(transform);
    }
}