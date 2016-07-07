package com.jadencode.main.renderengine.skybox;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Time;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class SkyboxShader extends ShaderProgram {

	private static final String VERTEX_FILE = "shaders/skyboxVertexShader.glsl";
	private static final String FRAGMENT_FILE = "shaders/skyboxFragmentShader.glsl";

    public final Uniform<Matrix4f> PROJECTION_MATRIX = new UniformSingle<>("projectionMatrix", this, this::load);
    public final Uniform<Matrix4f> VIEW_MATRIX = new UniformSingle<>("viewMatrix", this, this::load);
    public final Uniform<Vector3f> FOG_COLOR = new UniformSingle<>("fogColor", this, this::load);
    public final Uniform<Float> BLEND_FACTOR = new UniformSingle<>("blendFactor", this, this::load);
    public final Uniform<Integer> CUBE_MAP_1 = new UniformSingle<>("cubeMap1", this, this::load);
    public final Uniform<Integer> CUBE_MAP_2 = new UniformSingle<>("cubeMap2", this, this::load);

	public SkyboxShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
    public void connectTextureUnits() {
        this.loadInt(CUBE_MAP_1, 0);
        this.loadInt(CUBE_MAP_2, 1);
    }
	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
	}

}
