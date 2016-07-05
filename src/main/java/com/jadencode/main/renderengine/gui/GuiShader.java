package com.jadencode.main.renderengine.gui;

import com.jadencode.main.renderengine.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by gtrpl on 7/5/2016.
 */
public class GuiShader extends ShaderProgram {
    private static final String VERTEX_FILE = "shaders/guiVertexShader.glsl";
    private static final String FRAGMET_FILE = "shaders/guiFragmentShader.glsl";

    private int location_transformationMatrix;

    public GuiShader() {
        super(VERTEX_FILE, FRAGMET_FILE);
    }
    public void loadTransformation(Matrix4f matrix) {
        this.loadMatrix(this.location_transformationMatrix, matrix);
    }
    @Override
    protected void getAllUniformLocations() {
        this.location_transformationMatrix = this.getUniformLocation("transformationMatrix");
    }
    @Override
    public void bindAttributes() {
        this.bindAttribute(0, "position");
    }
}
