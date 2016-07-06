package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.Maths;
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

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int[] location_lightPosition;
    private int[] location_lightColor;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_useFakeLighting;
    private int location_fogDensity;
    private int location_fogGradient;
    private int location_skyColor;
    private int location_numberOfRows;
    private int location_offset;
    private int[] location_attenuation;

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
        this.location_transformationMatrix = this.getUniformLocation("transformationMatrix");
        this.location_projectionMatrix = this.getUniformLocation("projectionMatrix");
        this.location_viewMatrix = this.getUniformLocation("viewMatrix");
        this.location_shineDamper = this.getUniformLocation("shineDamper");
        this.location_reflectivity = this.getUniformLocation("reflectivity");
        this.location_useFakeLighting = this.getUniformLocation("useFakeLighting");
        this.location_fogDensity = this.getUniformLocation("fogDensity");
        this.location_fogGradient = this.getUniformLocation("fogGradient");
        this.location_skyColor = this.getUniformLocation("skyColor");
        this.location_numberOfRows = this.getUniformLocation("numberOfRows");
        this.location_offset = this.getUniformLocation("offset");

        this.location_lightPosition = new int[MAX_LIGHTS];
        this.location_lightColor = new int[MAX_LIGHTS];
        this.location_attenuation = new int[MAX_LIGHTS];

        for(int i = 0; i < MAX_LIGHTS; i++) {
            this.location_lightPosition[i] = this.getUniformLocation("lightPosition[" + i + "]");
            this.location_lightColor[i] = this.getUniformLocation("lightColor[" + i + "]");
            this.location_attenuation[i] = this.getUniformLocation("attenuation[" + i + "]");
        }
    }

    public void loadFogValues(float density, float gradient) {
        this.loadFloat(location_fogDensity, density);
        this.loadFloat(location_fogGradient, gradient);
    }
    public void loadNumberOfRows(int num) {
        this.loadFloat(this.location_numberOfRows, num);
    }
    public void loadOffset(Vector2f offset) {
        this.loadVector(this.location_offset, offset);
    }
    public void loadSkyColor(Vector3f skyColor) {
        this.loadVector(this.location_skyColor, skyColor);
    }

    public void loadUseFakeLighting(boolean val) {
        this.loadBoolean(this.location_useFakeLighting, val);
    }

    public void loadShineVariables(float damper, float reflect) {
        this.loadFloat(this.location_shineDamper, damper);
        this.loadFloat(this.location_reflectivity, reflect);
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        this.loadMatrix(this.location_transformationMatrix, matrix);
    }

    public void loadLights(List<Light> lights) {
        for(int i = 0; i < MAX_LIGHTS; i++) {
            if(i < lights.size()) {
                this.loadVector(this.location_lightPosition[i], lights.get(i).getPosition());
                this.loadVector(this.location_lightColor[i], lights.get(i).getColor());
                this.loadVector(this.location_attenuation[i], lights.get(i).getAttenuation());
            } else {
                this.loadVector(this.location_lightPosition[i], new Vector3f(0, 0, 0));
                this.loadVector(this.location_lightColor[i], new Vector3f(0, 0, 0));
                this.loadVector(this.location_attenuation[i], new Vector3f(1, 0, 0));
            }
        }
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        this.loadMatrix(this.location_viewMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        this.loadMatrix(this.location_projectionMatrix, matrix);
    }
}