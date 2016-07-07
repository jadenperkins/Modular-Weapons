package com.jadencode.main.renderengine.terrain;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.MasterRenderer;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.toolbox.Maths;
import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

public class WaterRenderer {

	private static final String DUDV_MAP = "waterDUDV";
	private static final String NORMAL_MAP = "waterNormal";
	private static final float WAVE_SPEED = 0.03F;

	private RawModel quad;
	private WaterShader shader;
	private final WaterFrameBuffers frameBuffers;
	private final int dudvTexture;
	private final int normalTexture;

	private float moveFactor = 0;

	public WaterRenderer(Loader loader, WaterShader shader, Matrix4f projectionMatrix, WaterFrameBuffers fbos) {
		this.shader = shader;
		this.frameBuffers = fbos;
		this.dudvTexture = loader.loadTexture(DUDV_MAP);
		this.normalTexture = loader.loadTexture(NORMAL_MAP);
		shader.start();
		shader.connectTextureUnits();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		setUpVAO(loader);
	}

	public void render(List<WaterTile> water, Camera camera, Light sun) {
		prepareRender(camera, sun);
		for (WaterTile tile : water) {
			Matrix4f modelMatrix = Maths.createTransformationMatrix(tile);
			shader.loadModelMatrix(modelMatrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		}
		unbind();
	}
	
	private void prepareRender(Camera camera, Light sun) {
		shader.start();
		shader.loadViewMatrix(camera);
		this.moveFactor += WAVE_SPEED * Time.getFrameTimeSeconds();
		this.moveFactor %= 1F;
		this.shader.MOVE_FACTOR.load(this.moveFactor);
		this.shader.LIGHT_POSITION.load(sun.getPosition());
		this.shader.LIGHT_COLOR.load(sun.getColor());
		this.shader.NEAR_PLANE.load(MasterRenderer.nearPlane);
		this.shader.FAR_PLANE.load(MasterRenderer.farPlane);
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, frameBuffers.getReflectionTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, frameBuffers.getRefractionTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, dudvTexture);
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, normalTexture);
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, frameBuffers.getRefractionDepthTexture());
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	private void unbind(){
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	private void setUpVAO(Loader loader) {
		// Just x and z vectex positions here, y is set to 0 in v.shader
		float[] vertices = { -1, -1, -1, 1, 1, -1, 1, -1, -1, 1, 1, 1 };
		quad = loader.loadToVAO(vertices, 2);
	}

}
