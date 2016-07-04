package com.jadencode.main.content.loaders.scripts;

import com.jadencode.main.scripts.ScriptBase;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Jaden on 11/4/2014.
 */
public abstract class ScriptLoader<S extends ScriptBase> {
    private final String typeName;
    private final Class<S> scriptClass;
    private final HashMap<String, S> scriptMap;

    public ScriptLoader(String typeName, Class<S> c, HashMap<String, S> map) {
        this.typeName = typeName;
        this.scriptClass = c;
        this.scriptMap = map;

        this.load(null, "");
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void load(String scriptName, String string) {
        try {
            S script = this.newScript(scriptName, string);
            this.scriptMap.putIfAbsent(scriptName, script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public S newScript(String name, String contents) throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        return this.scriptClass.getConstructor(String.class, String.class).newInstance(name, contents);
    }
}