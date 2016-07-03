package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.toolbox.Maths;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class StaticShader extends ShaderProgram {
    private static final String SRC_PATH = "";
    private static final String VERT_FILE = "shaders/vertexShader.txt";
    private static final String FRAG_FILE = "shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    public StaticShader() {
        super(VERT_FILE, FRAG_FILE);
    }
    @Override
    public void bindAttributes() {
        this.bindAttribute(0, "position");
        this.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        this.location_transformationMatrix = this.getUniformLocation("transformationMatrix");
        this.location_projectionMatrix = this.getUniformLocation("projectionMatrix");
        this.location_viewMatrix = this.getUniformLocation("viewMatrix");
    }
    public void loadTransformationMatrix(Matrix4f matrix) {
        this.loadMatrix(this.location_transformationMatrix, matrix);
    }
    public void loadViewMatrix(Camera camera) {
        Matrix4f matrix = Maths.createViewMatrix(camera);
        this.loadMatrix(this.location_viewMatrix, matrix);
    }
    public void loadProjectionMatrix(Matrix4f matrix) {
        this.loadMatrix(this.location_projectionMatrix, matrix);
    }
}