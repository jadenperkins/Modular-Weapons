package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.ShaderUniform;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class EntityShader extends ShaderProgram {
    private static final String SRC_PATH = "";
    private static final int MAX_LIGHTS = 4;
    private static final String VERT_FILE = "shaders/entityVertexShader.glsl";
    private static final String FRAG_FILE = "shaders/entityFragmentShader.glsl";

    public final ShaderUniform<Matrix4f> TRANSFORMATION_MATRIX = new ShaderUniform<>("transformationMatrix", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Matrix4f> PROJECTION_MATRIX = new ShaderUniform<>("projectionMatrix", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Matrix4f> VIEW_MATRIX = new ShaderUniform<>("viewMatrix", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Float> SHINE_DAMPER = new ShaderUniform<>("shineDamper", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Float> REFLECTIVITY = new ShaderUniform<>("reflectivity", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Boolean> USE_FAKE_LIGHTING = new ShaderUniform<>("useFakeLighting", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Float> FOG_DENSITY = new ShaderUniform<>("fogDensity", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Float> FOG_GRADIENT = new ShaderUniform<>("fogGradient", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Vector3f> SKY_COLOR = new ShaderUniform<>("skyColor", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Integer> NUMBER_OF_ROWS = new ShaderUniform<>("numberOfRows", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Vector2f> OFFSET = new ShaderUniform<>("offset", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Vector3f> LIGHT_POSITION = new ShaderUniform<>("lightPosition", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Vector3f> LIGHT_COLOR = new ShaderUniform<>("lightColor", this, (i, v) -> this.load(i, v));
    public final ShaderUniform<Vector3f> ATTENUATION = new ShaderUniform<>("attenuation", this, (i, v) -> this.load(i, v));


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
//    public void loadLights(List<Light> lights) {
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
//    }
//    public void loadViewMatrix(Camera camera) {
//        Matrix4f matrix = Maths.createViewMatrix(camera);
//        this.loadMatrix(VIEW_MATRIX, matrix);
//    }
//    public void loadProjectionMatrix(Matrix4f matrix) {
//        this.loadMatrix(PROJECTION_MATRIX, matrix);
//    }
}