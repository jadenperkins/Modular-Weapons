package com.jadencode.main.renderengine;

import com.jadencode.main.renderengine.toolbox.Uniform;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

/**
 * Created by gtrpl on 7/3/2016.
 */
public abstract class ShaderProgram {
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    private int programID;
    private int vertID;
    private int fragID;
    private final HashMap<String, Integer> uniformLocations = new HashMap<>();

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
    private int getUniformLocation(String uniform) {
        return GL20.glGetUniformLocation(this.programID, uniform);
    }
    private int bindLocation(String name) {
        if(this.uniformLocations.containsKey(name)) return this.uniformLocations.get(name);
        int location = this.getUniformLocation(name);
        this.uniformLocations.put(name, location);
        return location;
    }
    public int bindUniform(Uniform uniform) {
        return this.bindLocation(uniform.getName());
    }
    public int bindUniform(Uniform uniform, int index) {
        String resolved = String.format("%s[%d]", uniform.getName(), index);
        return this.bindLocation(resolved);
    }
    public int[] bindUniforms(Uniform uniform, int amount) {
        int[] locations = new int[amount];
        for(int i = 0; i < amount; i++) {
            locations[i] = this.bindUniform(uniform, i);
        }
        return locations;
    }
    private int getLocation(String name) {
        return this.uniformLocations.get(name);
    }
    protected int getLocation(Uniform uniform) {
        return this.getLocation(uniform.getName());
    }
    protected int getLocation(Uniform uniform, int index) {
        return this.getLocation(String.format("%s[%d]", uniform.getName(), index));
    }
    protected int[] getLocations(Uniform uniform, int amount) {
        int[] locations = new int[amount];
        for(int i = 0; i < amount; i++) {
            locations[i] = this.getLocation(uniform, i);
        }
        return locations;
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

    private void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }
    public void loadFloat(Uniform<Float> uniform, float value) {
        this.loadFloat(this.getLocation(uniform), value);
    }

    private void loadInt(int location, int value) {
        GL20.glUniform1i(location, value);
    }
    public void loadInt(Uniform<Integer> uniform, int value) {
        this.loadInt(this.getLocation(uniform), value);
    }

    private void loadVector(int location, Vector3f value) {
        GL20.glUniform3f(location, value.getX(), value.getY(), value.getZ());
    }
    public void loadVector(Uniform<Vector3f> uniform, Vector3f value) {
        this.loadVector(this.getLocation(uniform), value);
    }

    private void loadVector(int location, Vector4f value) {
        GL20.glUniform4f(location, value.getX(), value.getY(), value.getZ(), value.getW());
    }
    public void loadVector(Uniform<Vector4f> uniform, Vector4f value) {
        this.loadVector(this.getLocation(uniform), value);
    }

    private void loadVector(int location, Vector2f value) {
        GL20.glUniform2f(location, value.getX(), value.getY());
    }
    public void loadVector(Uniform<Vector2f> uniform, Vector2f value) {
        this.loadVector(this.getLocation(uniform), value);
    }

    private void loadBoolean(int location, boolean value) {
        GL20.glUniform1f(location, value ? 1 : 0);
    }
    public void loadBoolean(Uniform<Boolean> uniform, boolean value) {
        this.loadBoolean(this.getLocation(uniform), value);
    }

    private void loadMatrix(int location, Matrix4f value) {
        value.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }
    public void loadMatrix(Uniform<Matrix4f> uniform, Matrix4f value) {
        this.loadMatrix(this.getLocation(uniform), value);
    }

    protected void load(int uniform, float value) {
        this.loadFloat(uniform, value);
    }
    protected void load(int uniform, int value) {
        this.loadInt(uniform, value);
    }
    protected void load(int uniform, Vector2f value) {
        this.loadVector(uniform, value);
    }
    protected void load(int uniform, Vector3f value) {
        this.loadVector(uniform, value);
    }
    protected void load(int uniform, Vector4f value) {
        this.loadVector(uniform, value);
    }
    protected void load(int uniform, boolean value) {
        this.loadBoolean(uniform, value);
    }
    protected void load(int uniform, Matrix4f value) {
        this.loadMatrix(uniform, value);
    }


    protected abstract void getAllUniformLocations();
    public abstract void bindAttributes();
}