package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class EntityShader extends ShaderProgram {
    private static final String SRC_PATH = "";
    private static final String VERT_FILE = "shaders/vertexShader.txt";
    private static final String FRAG_FILE = "shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColor;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_useFakeLighting;
    private int location_skyColor;

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
        this.location_lightPosition = this.getUniformLocation("lightPosition");
        this.location_lightColor = this.getUniformLocation("lightColor");
        this.location_shineDamper = this.getUniformLocation("shineDamper");
        this.location_reflectivity = this.getUniformLocation("reflectivity");
        this.location_useFakeLighting = this.getUniformLocation("useFakeLighting");
        this.location_skyColor = this.getUniformLocation("skyColor");
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
    public void loadLight(Light light) {
        this.loadVector(this.location_lightPosition, light.getPosition());
        this.loadVector(this.location_lightColor, light.getColor());
    }
    public void loadViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        this.loadMatrix(this.location_viewMatrix, matrix);
    }
    public void loadProjectionMatrix(Matrix4f matrix) {
        this.loadMatrix(this.location_projectionMatrix, matrix);
    }
}