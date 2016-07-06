package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformArray;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

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
//        this.bindLocation(TRANSFORMATION_MATRIX);
//        this.bindLocation(PROJECTION_MATRIX);
//        this.bindLocation(VIEW_MATRIX);
//        this.bindLocation(SHINE_DAMPER);
//        this.bindLocation(REFLECTIVITY);
//        this.bindLocation(USE_FAKE_LIGHTING);
//        this.bindLocation(FOG_DENSITY);
//        this.bindLocation(FOG_GRADIENT);
//        this.bindLocation(SKY_COLOR);
//        this.bindLocation(NUMBER_OF_ROWS);
//        this.bindLocation(OFFSET);
//
//        this.bindLocations(LIGHT_POSITION, MAX_LIGHTS);
//        this.bindLocations(LIGHT_COLOR, MAX_LIGHTS);
//        this.bindLocations(ATTENUATION, MAX_LIGHTS);
    }
//    public void loadFogValues(float density, float gradient) {
//        this.loadFloat(FOG_DENSITY, density);
//        this.loadFloat(FOG_GRADIENT, gradient);
//    }
//    public void loadNumberOfRows(int num) {
//        this.loadFloat(NUMBER_OF_ROWS, num);
//    }
//    public void loadOffset(Vector2f offset) {
//        this.loadVector(OFFSET, offset);
//    }
//    public void loadSkyColor(Vector3f skyColor) {
//        this.loadVector(SKY_COLOR, skyColor);
//    }
//    public void loadUseFakeLighting(boolean val) {
//        this.loadBoolean(USE_FAKE_LIGHTING, val);
//    }
//    public void loadShineVariables(float damper, float reflect) {
//        this.loadFloat(SHINE_DAMPER, damper);
//        this.loadFloat(REFLECTIVITY, reflect);
//    }
//    public void loadTransformationMatrix(Matrix4f matrix) {
//        this.loadMatrix(TRANSFORMATION_MATRIX, matrix);
//    }
    public void loadLights(List<Light> lights) {
        this.LIGHT_POSITION.load(lights.stream().map(Light::getPosition).collect(Collectors.toList()));
        this.LIGHT_COLOR.load(lights.stream().map(Light::getColor).collect(Collectors.toList()));
        this.ATTENUATION.load(lights.stream().map(Light::getAttenuation).collect(Collectors.toList()));
//        for(int i = 0; i < MAX_LIGHTS; i++) {
//            if(i < lights.size()) {
//                this.loadVector(LIGHT_POSITION + "[" + i + "]", lights.get(i).getPosition());
//                this.loadVector(LIGHT_COLOR + "[" + i + "]", lights.get(i).getColor());
//                this.loadVector(ATTENUATION + "[" + i + "]", lights.get(i).getAttenuation());
//            } else {
//                this.loadVector(LIGHT_POSITION + "[" + i + "]", new Vector3f(0, 0, 0));
//                this.loadVector(LIGHT_COLOR + "[" + i + "]", new Vector3f(0, 0, 0));
//                this.loadVector(ATTENUATION + "[" + i + "]", new Vector3f(1, 0, 0));
//            }
//        }
    }
//    public void loadViewMatrix(Camera camera) {
//        Matrix4f matrix = Maths.createViewMatrix(camera);
//        this.loadMatrix(VIEW_MATRIX, matrix);
//    }
//    public void loadProjectionMatrix(Matrix4f matrix) {
//        this.loadMatrix(PROJECTION_MATRIX, matrix);
//    }
}