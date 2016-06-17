package com.jadencode.main.content.scripts;

import java.io.File;

/**
 * Created by Jaden on 11/4/2014.
 */
public abstract class ScriptLoader<S extends ScriptBase> {
    private final File directory;

    public ScriptLoader() {
        this.directory = new File("plugins/scripts", getTypeName());
        this.directory.mkdirs();
    }
    public File getDirectory() {
        return this.directory;
    }
    public abstract String getTypeName();
    public abstract void load(String fileName, String string);
}