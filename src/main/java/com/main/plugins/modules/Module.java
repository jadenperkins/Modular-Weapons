package com.main.plugins.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.pipeline.PipelineObject;

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
    private final int loadOrder;
    private final Function<Plugin, List<P>> contentGetter;

    public Module(String name, int loadOrder, Function<Plugin, List<P>> contentGetter) {
        this.moduleName = name;
        this.loadOrder = loadOrder;
        this.contentGetter = contentGetter;
    }
    public void loadContent(Plugin plugin) {
        List<P> content = getContent(plugin);
        for (P object : content) {
            this.consume(object);
        }
    }
    public void loadContent(Plugin plugin, Map<String, Plugin> objectOwners) {
        List<P> content = getContent(plugin);

        for (P pipelineObject : content) {
            objectOwners.put(pipelineObject.getName(), plugin);
            this.addItem(pipelineObject);
        }
    }
    public int getLoadOrder() {
        return loadOrder;
    }
    public List<P> getContent(Plugin plugin) {
        return contentGetter.apply(plugin);
    }
    public String getModuleName() {
        return this.moduleName;
    }
    public P getItem(String name) {
        return this.objects.get(name);
    }
    public void addItem(String name, ContentEditor<P> contentEditor, Plugin activePlugin) {
        P item = this.createItem(contentEditor, name);
        List<P> contentList = contentGetter.apply(activePlugin);
        for (P object : contentList) {
            if (object.getName().equals(name)) return;
        }
        contentList.add(item);
        addItem(item);
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

    public void onOpened(ContentEditor<P> contentEditor, PluginBuilderPanel panel) {

    }
    public void postInit() {

    }
    public abstract P createItem(ContentEditor<P> contentEditor, String name);
    public abstract void populatePanel(ContentEditor<P> contentEditor);
    public abstract void loadObject(ContentEditor<P> contentEditor, P pipelineObject);
    public abstract P getDefault();

    //The engine uses this to load the objects into runtime versions
    public abstract void consume(P pipelineObject);
}