package com.jadencode.main.renderengine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

/**
 * Created by gtrpl on 7/3/2016.
 */
public abstract class ShaderProgram {
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    private int programID;
    private int vertID;
    private int fragID;

    public ShaderProgram(String vertex, String fragment) {
        this.vertID = loadShader(vertex, GL20.GL_VERTEX_SHADER);
        this.fragID = loadShader(fragment, GL20.GL_FRAGMENT_SHADER);
        this.programID = GL20.glCreateProgram();
        GL20.glAttachShader(this.programID, this.vertID);
        GL20.glAttachShader(this.programID, this.fragID);
        this.bindAttributes();
        GL20.glLinkProgram(this.programID);
        GL20.glValidateProgram(this.programID);
        this.getAllUniformLocations();
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.out.println(file);
            System.exit(-1);
        }
        return shaderID;
    }

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(this.programID, uniformName);
    }

    public void start() {
        GL20.glUseProgram(this.programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        this.stop();
        GL20.glDetachShader(this.programID, this.vertID);
        GL20.glDetachShader(this.programID, this.fragID);
        GL20.glDeleteShader(this.vertID);
        GL20.glDeleteShader(this.fragID);
        GL20.glDeleteProgram(this.programID);
    }

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(this.programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value) {
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int location, Vector3f value) {
        GL20.glUniform3f(location, value.getX(), value.getY(), value.getZ());
    }

    protected void loadBoolean(int location, boolean value) {
        GL20.glUniform1f(location, value ? 1 : 0);
    }

    protected void loadMatrix(int location, Matrix4f value) {
        value.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

    protected abstract void getAllUniformLocations();

    public abstract void bindAttributes();
}