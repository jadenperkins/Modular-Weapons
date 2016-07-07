package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformArray;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class EntityShader extends ShaderProgram {
    private static final String SRC_PATH = "";
    private static final int MAX_LIGHTS = 4;
    private static final String VERT_FILE = "shaders/entityVertexShader.glsl";
    private static final String FRAG_FILE = "shaders/entityFragmentShader.glsl";

    public final Uniform<Matrix4f> TRANSFORMATION_MATRIX = new UniformSingle<>("transformationMatrix", this, this::load);
    public final Uniform<Matrix4f> PROJECTION_MATRIX = new UniformSingle<>("projectionMatrix", this, this::load);
    public final Uniform<Matrix4f> VIEW_MATRIX = new UniformSingle<>("viewMatrix", this, this::load);
    public final Uniform<Float> SHINE_DAMPER = new UniformSingle<>("shineDamper", this, this::load);
    public final Uniform<Float> REFLECTIVITY = new UniformSingle<>("reflectivity", this, this::load);
    public final Uniform<Boolean> USE_FAKE_LIGHTING = new UniformSingle<>("useFakeLighting", this, this::load);
    public final Uniform<Float> FOG_DENSITY = new UniformSingle<>("fogDensity", this, this::load);
    public final Uniform<Float> FOG_GRADIENT = new UniformSingle<>("fogGradient", this, this::load);
    public final Uniform<Vector3f> SKY_COLOR = new UniformSingle<>("skyColor", this, this::load);
    public final Uniform<Integer> NUMBER_OF_ROWS = new UniformSingle<>("numberOfRows", this, this::load);
    public final Uniform<Vector2f> OFFSET = new UniformSingle<>("offset", this, this::load);
    public final Uniform<Vector4f> CLIP_PLANE = new UniformSingle<>("clipPlane", this, this::load);
    public final Uniform<Vector3f> LIGHT_POSITION = new UniformArray<>("lightPosition", this, this::load, () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
    public final Uniform<Vector3f> LIGHT_COLOR = new UniformArray<>("lightColor", this, this::load, () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
    public final Uniform<Vector3f> ATTENUATION = new UniformArray<>("attenuation", this, this::load, () -> new Vector3f(1, 0, 0), MAX_LIGHTS);

    public EntityShader() {
        super(VERT_FILE, FRAG_FILE);
    }

    @Override
    public void bindAttributes() {
        this.bindAttribute(0, "position");
        this.bindAttribute(1, "textureCoords");
        this.bindAttribute(2, "normal");
    }
    @Override
    protected void getAllUniformLocations() {
    }
}