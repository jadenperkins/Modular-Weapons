package com.jadencode.main.renderengine.terrain;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.toolbox.Maths;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class WaterShader extends ShaderProgram {

	private final static String VERTEX_FILE = "shaders/waterVertexShader.glsl";
	private final static String FRAGMENT_FILE = "shaders/waterFragmentShader.glsl";

	public final Uniform<Matrix4f> MODEL_MATRIX = new UniformSingle<>("modelMatrix", this, this::load);
	public final Uniform<Matrix4f> VIEW_MATRIX = new UniformSingle<>("viewMatrix", this, this::load);
	public final Uniform<Matrix4f> PROJECTION_MATRIX = new UniformSingle<>("projectionMatrix", this, this::load);

	public final Uniform<Integer> REFLECTION_TEXTURE = new UniformSingle<>("reflectionTexture", this, this::load);
	public final Uniform<Integer> REFRACTION_TEXTURE = new UniformSingle<>("refractionTexture", this, this::load);
	public final Uniform<Integer> DUDV_MAP = new UniformSingle<>("dudvMap", this, this::load);
	public final Uniform<Integer> NORMAL_MAP = new UniformSingle<>("normalMap", this, this::load);

	public final Uniform<Float> MOVE_FACTOR = new UniformSingle<>("moveFactor", this, this::load);
	public final Uniform<Vector3f> CAMERA_POSITION = new UniformSingle<>("cameraPosition", this, this::load);

	public final Uniform<Vector3f> LIGHT_COLOR = new UniformSingle<>("lightColor", this, this::load);
	public final Uniform<Vector3f> LIGHT_POSITION = new UniformSingle<>("lightPosition", this, this::load);
	public final Uniform<Integer> DEPTH_MAP = new UniformSingle<>("depthMap", this, this::load);

	public final Uniform<Float> NEAR_PLANE = new UniformSingle<>("nearPlane", this, this::load);
	public final Uniform<Float> FAR_PLANE = new UniformSingle<>("farPlane", this, this::load);

	public WaterShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
	}

	public void connectTextureUnits() {
		REFLECTION_TEXTURE.load(0);
		REFRACTION_TEXTURE.load(1);
		DUDV_MAP.load(2);
		NORMAL_MAP.load(3);
		DEPTH_MAP.load(4);
	}

	public void loadProjectionMatrix(Matrix4f projection) {
		this.PROJECTION_MATRIX.load(projection);
	}
	public void loadViewMatrix(Camera camera){
		this.CAMERA_POSITION.load(camera.getPosition());
		this.VIEW_MATRIX.load(Maths.createViewMatrix(camera));
	}
	public void loadModelMatrix(Matrix4f modelMatrix){
		this.MODEL_MATRIX.load(modelMatrix);
	}

}
