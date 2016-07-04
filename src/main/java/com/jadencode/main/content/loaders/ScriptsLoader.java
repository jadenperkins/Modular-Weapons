package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.content.loaders.scripts.ScriptLoader;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ScriptsLoader extends ContentManager {
    private final HashMap<String, ScriptLoader> scriptLoaders = new HashMap<>();

    public ScriptsLoader() {
        super("Scripts", -1);

        Set<Class<? extends ScriptLoader>> managerClasses = new Reflections("com.jadencode.main.content.loaders.scripts").getSubTypesOf(ScriptLoader.class);

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
    public void consume(String name, JsonObject obj) {
        String type = obj.get("type").getAsString();
        if (this.scriptLoaders.containsKey(type)) {
            ScriptLoader loader = this.scriptLoaders.get(type);
            String contents = obj.get("script").getAsString();
            loader.load(name, contents);
        }
    }
}