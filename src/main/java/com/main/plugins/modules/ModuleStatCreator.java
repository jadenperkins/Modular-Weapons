package com.main.plugins.modules;

import com.main.constants.Stats;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderTextField;
import com.main.plugins.pipeline.PipelineObjectStat;
import com.main.scripts.ScriptStat;
import com.main.stat.StatBase;
import com.main.stat.StatDef;

import javax.swing.*;
import java.util.List;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleStatCreator extends Module<PipelineObjectStat> {
    public ModuleStatCreator() {
        super("Stats", 3, Plugin::getStats);
    }
    @Override
    public PipelineObjectStat createItem(ContentEditor<PipelineObjectStat> contentEditor, String name) {
        double value = contentEditor.getComponent("defaultValue").getAsTextField().doubleValue();
        return new PipelineObjectStat(name, (String) contentEditor.getComponent("scriptSelection").getAsComboBox().getSelectedItem(), value);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectStat> contentEditor) {
        contentEditor.addComponent("scriptSelection", new BuilderComboBox<>(), H_S, V_E, H_L, H_FLD);
        contentEditor.addComponent("defaultValue", new BuilderTextField(), H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectStat> contentEditor, PipelineObjectStat pipelineObject) {
        contentEditor.getComponent("scriptSelection").getAsComboBox().setSelectedItem(pipelineObject.getScript());
        contentEditor.getComponent("defaultValue").getAsTextField().setText(pipelineObject.getDefaultValue() + "");
    }
    @Override
    public void onOpened(ContentEditor<PipelineObjectStat> contentEditor, PluginBuilderPanel panel) {
        List<String> scripts = contentEditor.getScripts("stats", panel);
        contentEditor.getComponent("scriptSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));
    }
    @Override
    public PipelineObjectStat getDefault() {
        return new PipelineObjectStat("", "", 0.0);
    }

    @Override
    public void consume(PipelineObjectStat object) {
        double defaultValue = object.getDefaultValue();
        String scriptName = object.getScript();
        ScriptStat script = Stats.script(scriptName);
        StatBase stat = new StatDef(object.getName(), defaultValue, script);
        Stats.register(stat);
    }
}