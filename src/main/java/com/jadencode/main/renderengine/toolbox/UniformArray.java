package com.jadencode.main.renderengine.toolbox;

import com.jadencode.main.renderengine.ShaderProgram;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created by gtrpl on 7/6/2016.
 */
public class UniformArray<T> extends Uniform<T> {
    private final int[] locations;
    private final Supplier<T> defaulter;

    public UniformArray(String name, ShaderProgram shaderProgram, BiConsumer<Integer, T> consumer, Supplier<T> defaulter, int arraySize) {
        super(name, shaderProgram, consumer);
        this.locations = shaderProgram.bindUniforms(this, arraySize);
        this.defaulter = defaulter;
    }
    @Override
    public void load(T[] val) {
        for(int i = 0; i < locations.length; i++) {
            if(i < val.length) this.load(this.locations[i], val[i]);
            else this.load(this.locations[i], this.defaulter.get());
        }
    }
    @Override
    public void load(List<T> val) {
        for(int i = 0; i < locations.length; i++) {
            if(i < val.size()) {
                this.load(this.locations[i], val.get(i));
            } else {
                this.load(this.locations[i], this.defaulter.get());
            }
        }
    }
}