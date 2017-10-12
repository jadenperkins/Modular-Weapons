package com.main.content.loaders;

import com.main.content.Plugin;
import com.main.content.loaders.scripts.ScriptLoader;
import com.main.pipeline.PipelineObjectScript;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ScriptsLoader extends ContentManager<PipelineObjectScript> {
    private final HashMap<String, ScriptLoader> scriptLoaders = new HashMap<>();
    public ScriptsLoader() {
        super("scripts", -1, Plugin::getScripts);

        Set<Class<? extends ScriptLoader>> managerClasses = new Reflections("com.main.content.loaders.scripts").getSubTypesOf(ScriptLoader.class);

        for (Class<? extends ScriptLoader> managerClass : managerClasses) {
            try {
                ScriptLoader loader = managerClass.newInstance();
                scriptLoaders.put(loader.getTypeName(), loader);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void consume(PipelineObjectScript object) {
        String type = object.getType();
        if (!scriptLoaders.containsKey(type)) return;

        ScriptLoader loader = scriptLoaders.get(type);
        String contents = object.getScript();
        loader.load(object.getName(), contents);
    }
}