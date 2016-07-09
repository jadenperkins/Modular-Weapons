package com.jadencode.main.renderengine.shadows;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;

public class ShadowShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "shaders/shadowVertexShader.glsl";
	private static final String FRAGMENT_FILE = "shaders/shadowFragmentShader.glsl";

	public final Uniform<Matrix4f> MVP_MATRIX = new UniformSingle<>("mvpMatrix", this, this::load);

	protected ShadowShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "in_position");
		this.bindAttribute(1, "in_textureCoords");
	}
}
