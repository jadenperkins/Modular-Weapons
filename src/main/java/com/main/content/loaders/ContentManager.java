package com.main.content.loaders;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObject;

import java.util.List;
import java.util.function.Function;

/**
 * Created by JPERKI8 on 6/16/2016.
 */
public abstract class ContentManager<C extends PipelineObject> {
    private final String name;
    private final int loadOrder;
    private final Function<Plugin, List<C>> contentGetter;

    public ContentManager(String name, int loadOrder, Function<Plugin, List<C>> contentGetter) {
        this.name = name;
        this.loadOrder = loadOrder;
        this.contentGetter = contentGetter;
    }
    public String getName() {
        return name;
    }
    public int getLoadOrder() {
        return loadOrder;
    }
    public void loadContent(Plugin plugin) {
        List<C> content = contentGetter.apply(plugin);
        for (C c : content) this.consume(c);
    }
    public abstract void consume(C object);
}