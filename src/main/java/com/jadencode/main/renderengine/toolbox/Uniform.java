package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.ShaderProgram;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by JPERKI8 on 7/6/2016.
 */
public abstract class Uniform<T> {
    private final String name;
    private final BiConsumer<Integer, T> consumer;

    public Uniform(String name, ShaderProgram shaderProgram, BiConsumer<Integer, T> consumer) {
        this.name = name;
        this.consumer = consumer;
    }
    public String getName() {
        return this.name;
    }
    public void load(T val) {
        throw new IllegalArgumentException("Uniform " + name + " is not a single!");
    }
    public void load(T[] val) {
        throw new IllegalArgumentException("Uniform " + name + " is not an array!");
    }
    public void load(List<T> val) {
        throw new IllegalArgumentException("Uniform " + name + " is not an array!");
    }
    protected void load(int i, T val) {
        this.consumer.accept(i, val);
    }
}