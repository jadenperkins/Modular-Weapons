package com.main.pipeline;

public class PipelineObjectScripted extends PipelineObject {
    private final String script;

    public PipelineObjectScripted(String name, String script) {
        super(name);
        this.script = script;
    }
    public String getScript() {
        return script;
    }
}
