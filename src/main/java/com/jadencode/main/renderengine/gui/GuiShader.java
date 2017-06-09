package com.jadencode.main.renderengine.gui;

import com.jadencode.main.renderengine.ShaderProgram;
import com.jadencode.main.renderengine.toolbox.Uniform;
import com.jadencode.main.renderengine.toolbox.UniformSingle;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by gtrpl on 7/5/2016.
 */
public class GuiShader extends ShaderProgram {
    private static final String VERTEX_FILE = "shaders/guiVertexShader.glsl";
    private static final String FRAGMET_FILE = "shaders/guiFragmentShader.glsl";

    public final Uniform<Matrix4f> TRANSFORMATION_MATRIX = new UniformSingle<>("transformationMatrix", this, this::load);

    public GuiShader() {
        super(VERTEX_FILE, FRAGMET_FILE);
    }
    @Override
    public void bindAttributes() {
        this.bindAttribute(0, "position");
    }
}
