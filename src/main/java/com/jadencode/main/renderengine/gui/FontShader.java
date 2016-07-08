package com.jadencode.main.renderengine.gui;


import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class FontShader extends ShaderProgram {

	private static final String VERTEX_FILE = "shaders/fontVertex.glsl";
	private static final String FRAGMENT_FILE = "shaders/fontFragment.glsl";

	public final Uniform<Vector3f> COLOR = new UniformSingle<>("color", this, this::load);
	public final Uniform<Vector2f> TRANSLATION = new UniformSingle<>("translation", this, this::load);
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "textureCoords");
	}
}
