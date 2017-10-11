package com.main.content;

import com.main.pipeline.PipelineObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gtrpl on 6/20/2016.
 */
public class Plugin implements Comparable<Plugin> {
    private String pluginName;
    private List<String> dependencies;
    private Map<String, List<PipelineObject>> content;

    public Plugin(String pluginName) {
        this.pluginName = pluginName;
        this.dependencies = new ArrayList<>();
        this.content = new HashMap<>();
    }
    public String getPluginName() {
        return pluginName;
    }
    public List<PipelineObject> getContent(String contentGroup) {
        return !this.content.containsKey(contentGroup) ? new ArrayList<>() : this.content.get(contentGroup);
    }
    @Override
    public int compareTo(Plugin o) {
        return this.dependencies.contains(o.pluginName) ? 1 : -1;
    }
}