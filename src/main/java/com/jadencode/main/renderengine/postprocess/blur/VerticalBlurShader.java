package com.jadencode.main.renderengine.postprocess.blur;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;

public class VerticalBlurShader extends ShaderProgram {

	private static final String VERTEX_FILE = "shaders/verticalBlurVertex.glsl";
	private static final String FRAGMENT_FILE = "shaders/blurFragment.glsl";

	public final Uniform<Float> TARGET_HEIGHT = new UniformSingle<>("targetHeight", this, this::load);
	
	protected VerticalBlurShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		super.bindAttribute(0, "position");
	}
}