package com.jadencode.main.content.scripts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptMaterialType extends ScriptBase {
    private final List<String> exoticNames;
    public ScriptMaterialType(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
        this.exoticNames = this.getFieldDefault("exoticNames", new ArrayList<>());
    }
    public List<String> getExoticNames() {
        return this.exoticNames;
    }
}