package com.jadencode.main.renderengine.particles;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;

public class ParticleShader extends ShaderProgram {

	private static final String VERTEX_FILE = "shaders/particleVertexShader.glsl";
	private static final String FRAGMENT_FILE = "shaders/particleFragmentShader.glsl";

	public final Uniform<Matrix4f> PROJECTION = new UniformSingle<>("projectionMatrix", this, this::load);
	public final Uniform<Integer> NUMBER_OF_ROWS = new UniformSingle<>("numberOfRows", this, this::load);
	public ParticleShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "modelViewMatrix");
		this.bindAttribute(5, "texOffsets");
		this.bindAttribute(6, "blendFactor");
	}
}
