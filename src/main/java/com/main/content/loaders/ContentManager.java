package com.main.content.loaders;

import com.main.pipeline.PipelineObject;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public abstract class ContentManager<C extends PipelineObject> {
    private final String name;
    private final int loadOrder;

    public ContentManager(String name, int loadOrder) {
        this.name = name;
        this.loadOrder = loadOrder;
    }
    public String getName() {
        return name;
    }
    public int getLoadOrder() {
        return loadOrder;
    }
    public abstract void consume(C object);
}