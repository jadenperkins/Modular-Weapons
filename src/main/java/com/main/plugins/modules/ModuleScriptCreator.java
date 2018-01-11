package com.main.plugins.modules;

import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderTextArea;
import com.main.plugins.loaders.ScriptLoader;
import com.main.plugins.pipeline.PipelineObjectScript;
import org.apache.commons.io.FileUtils;
import org.reflections.Reflections;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleScriptCreator extends Module<PipelineObjectScript> {
    private static final String[] SCRIPT_TYPES = {
            "material types", "weapons", "stats"
    };
    private static final String SCRIPT_VIEW = "scriptView";
    private static final String SCRIPT_TYPE_SELECTION = "scriptTypeSelection";

    public ModuleScriptCreator() {
        super("Scripts", -1, Plugin::getScripts);
    }
    @Override
    public PipelineObjectScript createItem(ContentEditor<PipelineObjectScript> contentEditor, String name) {
        String scriptType = (String) contentEditor.getComponent(SCRIPT_TYPE_SELECTION).getAsComboBox().getSelectedItem();
        String script = contentEditor.getComponent(SCRIPT_VIEW).getAsTextArea().getText();
        return new PipelineObjectScript(name, scriptType, script);
    }
    @Override
    public void populatePanel(ContentEditor<PipelineObjectScript> contentEditor) {
        contentEditor.addComponent(SCRIPT_VIEW, new BuilderTextArea(), H_E, V_S, 650, 650);

        JButton button = new JButton("Import Script");
        button.setLocation(H_S, V_E);
        button.setSize(H_L, H_BTN);
        contentEditor.add(button);
        button.addActionListener(e -> {
            JFileChooser scriptChooser = new JFileChooser();
            scriptChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".js");
                }

                @Override
                public String getDescription() {
                    return "JavaScript Files";
                }
            });

            scriptChooser.showOpenDialog(null);
            File file = scriptChooser.getSelectedFile();
            if (file == null) return;
            try {
                contentEditor.getComponent(SCRIPT_VIEW).getAsTextArea().setText(FileUtils.readFileToString(file, Charset.defaultCharset()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        contentEditor.addComponent(SCRIPT_TYPE_SELECTION, new BuilderComboBox<>(), H_S, V_E + H_BTN + V_PAD, H_L, H_FLD);
        contentEditor.getComponent(SCRIPT_TYPE_SELECTION).getAsComboBox().setModel(new DefaultComboBoxModel(SCRIPT_TYPES));
    }
    @Override
    public void loadObject(ContentEditor<PipelineObjectScript> contentEditor, PipelineObjectScript pipelineObject) {
        contentEditor.getComponent(SCRIPT_TYPE_SELECTION).getAsComboBox().setSelectedItem(pipelineObject.getType());
        contentEditor.getComponent(SCRIPT_VIEW).getAsTextArea().setText(pipelineObject.getScript());
    }
    @Override
    public PipelineObjectScript getDefault() {
        return new PipelineObjectScript("", "", "");
    }

    private final Map<String, ScriptLoader> scriptLoaders = new HashMap<>();
    @Override
    public void postInit() {
        Set<Class<? extends ScriptLoader>> managerClasses = new Reflections("com.main.plugins.loaders").getSubTypesOf(ScriptLoader.class);

        for (Class<? extends ScriptLoader> managerClass : managerClasses) {
            try {
                ScriptLoader loader = managerClass.newInstance();
                scriptLoaders.put(loader.getTypeName(), loader);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void consume(PipelineObjectScript object) {
        String type = object.getType();
        if (!scriptLoaders.containsKey(type)) return;

        ScriptLoader loader = scriptLoaders.get(type);
        String contents = object.getScript();
        loader.load(object.getName(), contents);
    }
}