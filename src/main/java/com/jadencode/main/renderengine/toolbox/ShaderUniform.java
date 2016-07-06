package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.ShaderProgram;

import java.util.function.BiConsumer;

/**
 * Created by JPERKI8 on 7/6/2016.
 */
public class ShaderUniform<T> {
    private final String name;
    private final int location;
    private final BiConsumer<Integer, T> consumer;

    public ShaderUniform(String name, ShaderProgram shaderProgram, BiConsumer<Integer, T> consumer) {
        this.name = name;
        this.consumer = consumer;
        this.location = shaderProgram.bindUniform(this);
    }
    public String getName() {
        return this.name;
    }
    public void load(T val) {
        this.consumer.accept(this.location, val);
    }
}