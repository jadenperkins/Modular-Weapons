package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformArray;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class TerrainShader extends ShaderProgram {
    private static final int MAX_LIGHTS = 4;
    private static final String SRC_PATH = "";
    private static final String VERT_FILE = "shaders/terrainVertexShader.glsl";
    private static final String FRAG_FILE = "shaders/terrainFragmentShader.glsl";

    public final Uniform<Matrix4f> TRANSFORMATION_MATRIX = new UniformSingle<>("transformationMatrix", this, (i, v) -> this.load(i, v));
    public final Uniform<Matrix4f> PROJECTION_MATRIX = new UniformSingle<>("projectionMatrix", this, (i, v) -> this.load(i, v));
    public final Uniform<Matrix4f> VIEW_MATRIX = new UniformSingle<>("viewMatrix", this, (i, v) -> this.load(i, v));
    public final Uniform<Float> SHINE_DAMPER = new UniformSingle<>("shineDamper", this, (i, v) -> this.load(i, v));
    public final Uniform<Float> REFLECTIVITY = new UniformSingle<>("reflectivity", this, (i, v) -> this.load(i, v));
    public final Uniform<Vector3f> SKY_COLOR = new UniformSingle<>("skyColor", this, (i, v) -> this.load(i, v));
    public final Uniform<Float> FOG_DENSITY = new UniformSingle<>("fogDensity", this, (i, v) -> this.load(i, v));
    public final Uniform<Float> FOG_GRADIENT = new UniformSingle<>("fogGradient", this, (i, v) -> this.load(i, v));
    public final Uniform<Integer> BACKGROUND_TEXTURE = new UniformSingle<>("backgroundTexture", this, (i, v) -> this.load(i, v));
    public final Uniform<Integer> R_TEXTURE = new UniformSingle<>("rTexture", this, (i, v) -> this.load(i, v));
    public final Uniform<Integer> G_TEXTURE = new UniformSingle<>("gTexture", this, (i, v) -> this.load(i, v));
    public final Uniform<Integer> B_TEXTURE = new UniformSingle<>("bTexture", this, (i, v) -> this.load(i, v));
    public final Uniform<Integer> BLEND_MAP = new UniformSingle<>("blendMap", this, (i, v) -> this.load(i, v));
    public final Uniform<Vector3f> LIGHT_POSITION = new UniformArray<>("lightPosition", this, (i, v) -> this.load(i, v), () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
    public final Uniform<Vector3f> LIGHT_COLOR = new UniformArray<>("lightColor", this, (i, v) -> this.load(i, v), () -> new Vector3f(0, 0, 0), MAX_LIGHTS);
    public final Uniform<Vector3f> ATTENUATION = new UniformArray<>("attenuation", this, (i, v) -> this.load(i, v), () -> new Vector3f(1, 0, 0), MAX_LIGHTS);

    public TerrainShader() {
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
//        this.bindLocation(SKY_COLOR);
//        this.bindLocation(BACKGROUND_TEXTURE);
//        this.bindLocation(R_TEXTURE);
//        this.bindLocation(G_TEXTURE);
//        this.bindLocation(B_TEXTURE);
//        this.bindLocation(BLEND_MAP);
//        this.bindLocation(FOG_DENSITY);
//        this.bindLocation(FOG_GRADIENT);
//
//        this.bindLocations(LIGHT_POSITION, MAX_LIGHTS);
//        this.bindLocations(LIGHT_COLOR, MAX_LIGHTS);
//        this.bindLocations(ATTENUATION, MAX_LIGHTS);
    }
    public void connectTextureUnits() {
        this.loadInt(BACKGROUND_TEXTURE, 0);
        this.loadInt(R_TEXTURE, 1);
        this.loadInt(G_TEXTURE, 2);
        this.loadInt(B_TEXTURE, 3);
        this.loadInt(BLEND_MAP, 4);
    }
//    public void loadSkyColor(Vector3f skyColor) {
//        this.loadVector(SKY_COLOR, skyColor);
//    }
//
//    public void loadShineVariables(float damper, float reflect) {
//        this.loadFloat(SHINE_DAMPER, damper);
//        this.loadFloat(REFLECTIVITY, reflect);
//    }
//
//    public void loadTransformationMatrix(Matrix4f matrix) {
//        this.loadMatrix(TRANSFORMATION_MATRIX, matrix);
//    }
//
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
//
//    public void loadViewMatrix(Camera camera) {
//        Matrix4f matrix = Maths.createViewMatrix(camera);
//        this.loadMatrix(VIEW_MATRIX, matrix);
//    }
//
//    public void loadProjectionMatrix(Matrix4f matrix) {
//        this.loadMatrix(PROJECTION_MATRIX, matrix);
//    }
//
//    public void loadFogValues(float density, float gradient) {
//        this.loadFloat(FOG_DENSITY, density);
//        this.loadFloat(FOG_GRADIENT, gradient);
//    }
}