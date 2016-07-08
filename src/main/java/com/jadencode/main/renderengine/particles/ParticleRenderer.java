package com.jadencode.main.renderengine.particles;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;

import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.models.RawModel;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class ParticleRenderer {
	
	private static final float[] VERTICES = {-0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, -0.5f};
	private static final int MAX_INSTANCE = 10000;
	private static final int INSTANCE_DATA_LENGTH = 21;

	private static final FloatBuffer buffer = BufferUtils.createFloatBuffer(MAX_INSTANCE * INSTANCE_DATA_LENGTH);


	private final RawModel quad;
	private final ParticleShader shader;

	private Loader loader;
	private int vboID;
	private int pointer = 0;
	
	protected ParticleRenderer(Loader loader, Matrix4f projectionMatrix) {
		this.loader = loader;
		this.vboID = loader.createEmptyVBO(INSTANCE_DATA_LENGTH * MAX_INSTANCE);
		quad = loader.loadToVAO(VERTICES, 2);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 1, 4, INSTANCE_DATA_LENGTH, 0);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 2, 4, INSTANCE_DATA_LENGTH, 4);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 3, 4, INSTANCE_DATA_LENGTH, 8);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 4, 4, INSTANCE_DATA_LENGTH, 12);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 5, 4, INSTANCE_DATA_LENGTH, 16);
		loader.addInstancedAttribute(quad.getVaoID(), vboID, 6, 1, INSTANCE_DATA_LENGTH, 20);

		shader = new ParticleShader();
		shader.start();
		shader.PROJECTION.load(projectionMatrix);
		shader.stop();
	}
	protected void render(Map<ParticleTexture, List<Particle>> particles, Camera camera){
		Matrix4f view = Maths.createViewMatrix(camera);
		this.prepare();
		for (ParticleTexture particleTexture : particles.keySet()) {
			this.bindTexture(particleTexture);
			List<Particle> particleList = particles.get(particleTexture);
			pointer = 0;
			float[] vboData = new float[particleList.size() * INSTANCE_DATA_LENGTH];
			shader.NUMBER_OF_ROWS.load(particleTexture.getNumberOfRows());
			for (Particle particle : particleList) {
				updateModelViewMatrix(particle.getTranslation(), particle.getRotation().getZ(), particle.getScale(), view, vboData);
				updateTexCoords(particle, vboData);
			}
			loader.updateVBO(vboID, vboData, buffer);
			GL31.glDrawArraysInstanced(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount(), particleList.size());
		}
		finishRendering();
	}
	private void bindTexture(ParticleTexture particleTexture) {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, particleTexture.getBlendingFunction());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, particleTexture.getTextureID());
	}
	private void updateTexCoords(Particle particle, float[] data) {
		data[pointer++] = particle.getCurrentOffset().x;
		data[pointer++] = particle.getCurrentOffset().y;
		data[pointer++] = particle.getNextOffset().x;
		data[pointer++] = particle.getNextOffset().y;
		data[pointer++] = particle.getBlendFactor();
	}
	private void updateModelViewMatrix(Vector3f position, float rotation, Vector3f scale, Matrix4f viewMatrix, float[] vboData) {
		Matrix4f modelMatrix = new Matrix4f();
		Matrix4f.translate(position, modelMatrix, modelMatrix);
		modelMatrix.m00 = viewMatrix.m00;
		modelMatrix.m01 = viewMatrix.m10;
		modelMatrix.m02 = viewMatrix.m20;
		modelMatrix.m10 = viewMatrix.m01;
		modelMatrix.m11 = viewMatrix.m11;
		modelMatrix.m12 = viewMatrix.m21;
		modelMatrix.m20 = viewMatrix.m02;
		modelMatrix.m21 = viewMatrix.m12;
		modelMatrix.m22 = viewMatrix.m22;
		Matrix4f.rotate((float) Math.toRadians(rotation), new Vector3f(0, 0, 1), modelMatrix, modelMatrix);
		Matrix4f.scale(scale, modelMatrix, modelMatrix);
		Matrix4f modelViewMatrix = Matrix4f.mul(viewMatrix, modelMatrix, null);
		this.storeMatrix(modelViewMatrix, vboData);
	}
	private void storeMatrix(Matrix4f matrix, float[] data) {
		data[pointer++] = matrix.m00;
		data[pointer++] = matrix.m01;
		data[pointer++] = matrix.m02;
		data[pointer++] = matrix.m03;
		data[pointer++] = matrix.m10;
		data[pointer++] = matrix.m11;
		data[pointer++] = matrix.m12;
		data[pointer++] = matrix.m13;
		data[pointer++] = matrix.m20;
		data[pointer++] = matrix.m21;
		data[pointer++] = matrix.m22;
		data[pointer++] = matrix.m23;
		data[pointer++] = matrix.m30;
		data[pointer++] = matrix.m31;
		data[pointer++] = matrix.m32;
		data[pointer++] = matrix.m33;
	}
	protected void cleanUp(){
		shader.cleanUp();
	}
	
	private void prepare() {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		GL20.glEnableVertexAttribArray(4);
		GL20.glEnableVertexAttribArray(5);
		GL20.glEnableVertexAttribArray(6);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
	}
	
	private void finishRendering(){
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(3);
		GL20.glDisableVertexAttribArray(4);
		GL20.glDisableVertexAttribArray(5);
		GL20.glDisableVertexAttribArray(6);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
}
