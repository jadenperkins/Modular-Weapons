package com.main.plugins.pipeline;

public class PipelineObjectScript extends PipelineObject {
    private final String type;
    private final String script;

    public PipelineObjectScript(String name, String type, String script) {
        super(name);
        this.type = type;
        this.script = script;
    }
    public String getType() {
        return type;
    }
    public String getScript() {
        return script;
    }
}
