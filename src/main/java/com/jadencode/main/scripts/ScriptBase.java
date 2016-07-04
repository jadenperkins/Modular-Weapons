package com.jadencode.main.scripts;

//import javax.script.*;

import org.mozilla.javascript.*;

/**
 * Created by Jaden on 11/4/2014.
 */
public class ScriptBase {

    private final ScriptableObject sharedScope;
    private final Script compiledScript;

    private final String scriptName;
    private final String scriptContents;

    public ScriptBase(String scriptName, String scriptContents) {
        this.scriptName = scriptName;
        this.scriptContents = scriptContents;

        Context ctx = Context.enter();
        ctx.setLanguageVersion(Context.VERSION_1_2);
        this.compiledScript = ctx.compileString(this.scriptContents, scriptName, 1, null);
        this.sharedScope = new ImporterTopLevel(ctx);
        this.compiledScript.exec(ctx, sharedScope);
    }

    public String getScriptContents() {
        return this.scriptContents;
    }

    public String getScriptName() {
        return this.scriptName;
    }

    protected Object getField(String name) {
        try {
            Object property = this.sharedScope.get(name, this.sharedScope);
            Object asJava = Context.jsToJava(property, Object.class);

            return asJava == UniqueTag.NOT_FOUND ? null : asJava;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T> T getFieldDefault(String name, T def) {
        T ret = (T) this.getField(name);
        return ret == null ? def : ret;
    }

    protected Object invoke(String name, Object... params) {
        try {
            Context localContext = Context.enter();
            Object asJava;
            try {
                Object function = this.sharedScope.get(name, this.sharedScope);

                if (function == UniqueTag.NOT_FOUND)
                    return null;

                Object result = ((Function) function).call(localContext, this.sharedScope, this.sharedScope, params);
                asJava = Context.jsToJava(result, Object.class);
            } finally {
                Context.exit();
            }
            return asJava == UniqueTag.NOT_FOUND ? null : asJava;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T> T invokeWithDefault(String name, T def, Object... params) {
        T ret = (T) this.invoke(name, params);
        return ret == null ? def : ret;
    }
}
