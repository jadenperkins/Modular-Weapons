package com.jadencode.main.renderengine.postprocess;

import com.jadencode.main.renderengine.ShaderProgram;

public class ContrastShader extends ShaderProgram {

	private static final String VERTEX_FILE = "shaders/contrastVertex.glsl";
	private static final String FRAGMENT_FILE = "shaders/contrastFragment.glsl";
	
	public ContrastShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
	}

}
