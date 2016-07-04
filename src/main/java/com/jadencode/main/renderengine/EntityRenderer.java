package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.textures.ModelTexture;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 7/2/2016.
 */
public class EntityRenderer {

    private EntityShader shader;

    public EntityRenderer(EntityShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    public void render(HashMap<TexturedModel, List<Entity>> entities) {
        for(TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> entityGroup = entities.get(model);
            for (Entity entity : entityGroup) {
                this.prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }
    private void prepareTexturedModel(TexturedModel texturedModel) {
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = texturedModel.getTexture();
        if(texture.getHasTransparency()) MasterRenderer.disableCulling();
        this.shader.loadUseFakeLighting(texture.getUseFakeLighting());
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
    }
    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }
    private void prepareInstance(Entity entity) {
        Matrix4f transform = Maths.createTransformationMatrix(entity);
        shader.loadTransformationMatrix(transform);
    }
}