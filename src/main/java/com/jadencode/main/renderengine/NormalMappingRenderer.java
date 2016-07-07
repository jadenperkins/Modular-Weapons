package com.jadencode.main.renderengine;


import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.textures.ModelTexture;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NormalMappingRenderer {

	private NormalMappingShader shader;

	public NormalMappingRenderer(NormalMappingShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		shader.start();
		shader.PROJECTION_MATRIX.load(projectionMatrix);
		shader.connectTextureUnits();
		shader.stop();
	}

	public void render(Map<TexturedModel, List<Entity>> entities) {
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}

	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		ModelTexture texture = model.getTexture();
		shader.NUMBER_OF_ROWS.load(texture.getNumberOfRows());
		if (texture.getHasTransparency()) {
			MasterRenderer.disableCulling();
		}
		shader.SHINE_DAMPER.load(texture.getShineDamper());
		shader.REFLECTIVITY.load(texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getNormalMap());
	}
	private void unbindTexturedModel() {
		MasterRenderer.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(3);
		GL30.glBindVertexArray(0);
	}
	private void prepareInstance(Entity entity) {
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity);
		shader.TRANSFORMATION_MATRIX.load(transformationMatrix);
		shader.OFFSET.load(entity.getTextureOffset());
	}
	private void prepare(Vector4f clipPlane, List<Light> lights, Camera camera) {
		shader.PLANE.load(clipPlane);
		shader.SKY_COLOR.load(MasterRenderer.getSkyColor());
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		shader.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
		shader.LIGHT_POSITION_EYES.load(lights.stream().map(l -> NormalMappingShader.getEyeSpacePosition(l, viewMatrix)).collect(Collectors.toList()));
		shader.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
	}
}
