package com.jadencode.main.renderengine;

/**
 * Created by gtrpl on 7/3/2016.
 */
public class StaticShader extends ShaderProgram {
    private static final String SRC_PATH = "";
    private static final String VERT_FILE = "shaders/vertexShader.txt";
    private static final String FRAG_FILE = "shaders/fragmentShader.txt";
    public StaticShader() {
        super(VERT_FILE, FRAG_FILE);
    }
    @Override
    public void bindAttributes() {
        this.bindAttribute(0, "position");
    }
}