package com.main.plugins.modules;

import com.main.constants.PartTypes;
import com.main.constants.StatSets;
import com.main.constants.WeaponTypes;
import com.main.generate.weapon.WeaponPartType;
import com.main.generate.weapon.WeaponType;
import com.main.plugins.Plugin;
import com.main.plugins.builder.ContentEditor;
import com.main.plugins.builder.PluginBuilderPanel;
import com.main.plugins.builder.component.BuilderComboBox;
import com.main.plugins.builder.component.BuilderTable;
import com.main.plugins.builder.component.BuilderTextField;
import com.main.plugins.pipeline.PipelineObjectWeaponType;
import com.main.scripts.ScriptWeapon;
import com.main.stat.StatSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.main.plugins.builder.ContentEditor.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ModuleWeaponTypeCreator extends Module<PipelineObjectWeaponType> {
    public ModuleWeaponTypeCreator() {
        super("Weapon Types", 5, Plugin::getWeaponTypes);
    }
    @Override
    public PipelineObjectWeaponType createItem(ContentEditor<PipelineObjectWeaponType> contentEditor, String name) {
        String stat = (String) contentEditor.getComponent("statSetSelection").getAsComboBox().getSelectedItem();
        String script = (String) contentEditor.getComponent("scriptSelection").getAsComboBox().getSelectedItem();
        float weight = contentEditor.getComponent("weightField").getAsTextField().floatValue();
        String primary = (String) contentEditor.getComponent("primarySelection").getAsComboBox().getSelectedItem();

        List<String> parts = new ArrayList<>();
        BuilderTable partsList = contentEditor.getComponent("partsList").getAsTable();
        int rows = partsList.getModel().getRowCount();
        for(int row = 0; row < rows; row++) {
            String part = (String) partsList.getModel().getValueAt(row, 0);
            if(part != null && !part.isEmpty()) parts.add(part);
        }

        return new PipelineObjectWeaponType(name, script, stat, weight, parts, primary);
    }

    @Override
    public void populatePanel(ContentEditor<PipelineObjectWeaponType> contentEditor) {
        contentEditor.addComponent("statSetSelection", new BuilderComboBox<>(), H_S, V_E, H_L, H_FLD);
        contentEditor.addComponent("scriptSelection", new BuilderComboBox<>(), H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        contentEditor.addComponent("weightField", new BuilderTextField(), H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("primarySelection", new BuilderComboBox<>(), H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        contentEditor.addComponent("partsList", new BuilderTable(), H_E, V_S, H_L, 10 * H_NTR);
    }

    @Override
    public void loadObject(ContentEditor<PipelineObjectWeaponType> contentEditor, PipelineObjectWeaponType pipelineObject) {
        contentEditor.getComponent("statSetSelection").getAsComboBox().setSelectedItem(pipelineObject.getStats());
        contentEditor.getComponent("scriptSelection").getAsComboBox().setSelectedItem(pipelineObject.getScript());
        contentEditor.getComponent("weightField").getAsTextField().setText(pipelineObject.getWeight() + "");
        contentEditor.getComponent("primarySelection").getAsComboBox().setSelectedItem(pipelineObject.getPrimary());

        BuilderTable partsList = contentEditor.getComponent("partsList").getAsTable();
        int rows = partsList.getModel().getRowCount();
        for (int row = 0; row < rows; row++) {
            partsList.getModel().setValueAt("", row, 0);
        }

        List<String> partTypes = pipelineObject.getParts();

        int size = partTypes.size();
        for (int i = 0; i < size; i++) {
            String partType = partTypes.get(i);
            partsList.getModel().setValueAt(partType, i, 0);
        }
    }

    @Override
    public void onOpened(ContentEditor<PipelineObjectWeaponType> contentEditor, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        contentEditor.getComponent("statSetSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = contentEditor.getScripts("weapons", panel);
        contentEditor.getComponent("scriptSelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = new ArrayList<>(partTypesModule.getItemKeys());

        partTypes.add("");
        JComboBox<String> box = new JComboBox<>(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        contentEditor.getComponent("primarySelection").getAsComboBox().setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        BuilderTable partsList = contentEditor.getComponent("partsList").getAsTable();
        partsList.setModel(new DefaultTableModel(partTypes.size(), 1));
        partsList.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
    }
    @Override
    public PipelineObjectWeaponType getDefault() {
        return new PipelineObjectWeaponType("", "", "", 1F, new ArrayList<>(), "");
    }

    @Override
    public void consume(PipelineObjectWeaponType object) {
        StatSet stats = StatSets.get(object.getStats());
        ScriptWeapon script = WeaponTypes.script(object.getScript());
        float weight = object.getWeight();
        List<String> partTypes = object.getParts();
        List<WeaponPartType> types = partTypes.stream().map(PartTypes::get).collect(Collectors.toList());
        WeaponPartType primary = object.getPrimary() != null ? PartTypes.get(object.getPrimary()) : types.get(0);

        WeaponType weapon = new WeaponType(object.getName(), weight, stats, primary, types, script);
        WeaponTypes.register(weapon);
    }
}