package com.jadencode.main.content.scripts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Jaden on 11/4/2014.
 */
public abstract class ScriptLoader<S extends ScriptBase> {
    private final File directory;
    private final Class<S> scriptClass;
    private final HashMap<String, S> scriptMap;

    public ScriptLoader(String typeName, Class<S> c, HashMap<String, S> map) {
        this.directory = new File("plugins/scripts", typeName);
        this.directory.mkdirs();
        this.scriptClass = c;
        this.scriptMap = map;
    }
    public File getDirectory() {
        return this.directory;
    }
    public void load(String scriptName, String string) {
        try {
            S script = this.scriptClass.getConstructor(String.class, String.class).newInstance(scriptName, string);
            this.scriptMap.putIfAbsent(scriptName, script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}