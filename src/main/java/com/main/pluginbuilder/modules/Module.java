package com.main.pluginbuilder.modules;

import com.main.content.Plugin;
import com.main.pipeline.PipelineObject;
import com.main.pluginbuilder.contenteditors.ContentEditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by gtrpl on 6/18/2016.
 */
public abstract class Module<P extends PipelineObject> {
    private final HashMap<String, P> objects = new HashMap<>();
    private final String moduleName;
    private final Function<Plugin, List<P>> contentGetter;

    public Module(String name, Function<Plugin, List<P>> contentGetter) {
        this.moduleName = name;
        this.contentGetter = contentGetter;
    }
    public void loadContent(Plugin plugin, Map<String, Plugin> objectOwners) {
        List<P> content = getContent(plugin);

        for (P pipelineObject : content) {
            objectOwners.put(pipelineObject.getName(), plugin);
            this.addItem(pipelineObject);
        }
    }
    public List<P> getContent(Plugin plugin) {
        return contentGetter.apply(plugin);
    }
    public abstract ContentEditor<P> getContentEditor();
    public String getModuleName() {
        return this.moduleName;
    }
    public P getItem(String name) {
        return this.objects.get(name);
    }
    public void addItem(String name) {
        addItem(this.createItem(name));
    }
    public void addItem(P object) {
        String name = object.getName();
        this.objects.put(name, object);
    }
    public void remove(String name) {
        this.objects.remove(name);
    }
    public List<String> getItemKeys() {
        List<String> itemKeys = new ArrayList<>(objects.keySet());
        itemKeys.sort(null);
        return itemKeys;
    }
    public HashMap<String, P> getObjects() {
        return this.objects;
    }
    @Override
    public String toString() {
        return this.getModuleName();
    }
    public P createItem(String name) {
        return this.getContentEditor().createItem(name);
    }
}