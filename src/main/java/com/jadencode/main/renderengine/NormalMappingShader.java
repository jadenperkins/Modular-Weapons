package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformArray;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class NormalMappingShader extends ShaderProgram {
	
	private static final int MAX_LIGHTS = 4;
	
	private static final String VERTEX_FILE = "shaders/normalMapVShader.glsl";
	private static final String FRAGMENT_FILE = "shaders/normalMapFShader.glsl";

	public final Uniform<Matrix4f> TRANSFORMATION_MATRIX = new UniformSingle<>("transformationMatrix", this, this::load);
	public final Uniform<Matrix4f> PROJECTION_MATRIX = new UniformSingle<>("projectionMatrix", this, this::load);
	public final Uniform<Matrix4f> VIEW_MATRIX = new UniformSingle<>("viewMatrix", this, this::load);
	public final Uniform<Vector3f> LIGHT_POSITION_EYES = new UniformArray<>("lightPositionEyeSpace", this, this::load, () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
	public final Uniform<Vector3f> LIGHT_COLOR = new UniformArray<>("lightColor", this, this::load, () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
	public final Uniform<Vector3f> ATTENUATION = new UniformArray<>("attenuation", this, this::load, () -> new Vector3f(1, 0, 0), MAX_LIGHTS);
	public final Uniform<Float> SHINE_DAMPER = new UniformSingle<>("shineDamper", this, this::load);
	public final Uniform<Float> REFLECTIVITY = new UniformSingle<>("reflectivity", this, this::load);
	public final Uniform<Vector3f> SKY_COLOR = new UniformSingle<>("skyColor", this, this::load);
	public final Uniform<Integer> NUMBER_OF_ROWS = new UniformSingle<>("numberOfRows", this, this::load);
	public final Uniform<Vector2f> OFFSET = new UniformSingle<>("offset", this, this::load);
	public final Uniform<Vector4f> PLANE = new UniformSingle<>("plane", this, this::load);
	public final Uniform<Integer> MODEL_TEXTURE = new UniformSingle<>("modelTexture", this, this::load);
	public final Uniform<Float> FOG_DENSITY = new UniformSingle<>("density", this, this::load);
	public final Uniform<Float> FOG_GRADIENT = new UniformSingle<>("gradient", this, this::load);
	public final Uniform<Integer> NORMAL_MAP = new UniformSingle<>("normalMap", this, this::load);

	public NormalMappingShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAttributes() {
		this.bindAttribute(0, "position");
		this.bindAttribute(1, "textureCoordinates");
		this.bindAttribute(2, "normal");
		this.bindAttribute(3, "tangents");
	}
	public void connectTextureUnits(){
		this.MODEL_TEXTURE.load(0);
		this.NORMAL_MAP.load(1);
	}
	public static Vector3f getEyeSpacePosition(Light light, Matrix4f viewMatrix) {
		Vector3f position = light.getPosition();
		Vector4f eyeSpacePos = new Vector4f(position.x, position.y, position.z, 1f);
		Matrix4f.transform(viewMatrix, eyeSpacePos, eyeSpacePos);
		return new Vector3f(eyeSpacePos);
	}
}
