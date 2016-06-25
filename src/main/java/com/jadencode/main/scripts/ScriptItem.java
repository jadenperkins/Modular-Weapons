package com.jadencode.main.scripts;

import com.jadencode.main.generate.item.instance.Item;

/**
 * Created by gtrpl on 6/17/2016.
 */
public class ScriptItem extends ScriptBase {
    public ScriptItem(String scriptName, String scriptContents) {
        super(scriptName, scriptContents);
    }
    public String getDisplayName(Item instance, String fallback) {
        return this.invokeWithDefault("getDisplayName", fallback, instance);
    }
}