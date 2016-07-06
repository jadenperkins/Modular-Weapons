package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.ShaderProgram;

import java.util.function.BiConsumer;

/**
 * Created by gtrpl on 7/6/2016.
 */
public class UniformSingle<T> extends Uniform<T> {
    private final int location;
    private final BiConsumer<Integer, T> consumer;

    public UniformSingle(String name, ShaderProgram shaderProgram, BiConsumer<Integer, T> consumer) {
        super(name, shaderProgram, consumer);
        this.consumer = consumer;
        this.location = shaderProgram.bindUniform(this);
    }
    @Override
    public void load(T val) {
        this.consumer.accept(this.location, val);
    }
}