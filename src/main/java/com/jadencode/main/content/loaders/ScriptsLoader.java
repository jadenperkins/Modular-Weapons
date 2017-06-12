package com.jadencode.main.content.loaders;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Strings;
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
        super(Strings.Loaders.SCRIPTS, -1);

        Set<Class<? extends ScriptLoader>> managerClasses = new Reflections(Strings.IO.PACKAGE_LOADERS_SCRIPTS).getSubTypesOf(ScriptLoader.class);

        for (Class<? extends ScriptLoader> managerClass : managerClasses) {
            try {
                ScriptLoader loader = managerClass.newInstance();
                scriptLoaders.put(loader.getTypeName(), loader);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void consume(String name, JsonObject obj) {
        String type = obj.get(Strings.JsonKey.TYPE).getAsString();
        if (!this.scriptLoaders.containsKey(type)) return;

        ScriptLoader loader = this.scriptLoaders.get(type);
        String contents = obj.get(Strings.JsonKey.SCRIPT).getAsString();
        loader.load(name, contents);
    }
}