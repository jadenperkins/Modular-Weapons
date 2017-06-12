package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonObject;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemTypeMaterialized;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemTypeMaterializedEditor extends ContentEditor<ContentObjectItemTypeMaterialized> {

    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> scriptSelection;
    private final JTextField weightField;
    private final JTextField infoField;
    private final JList<String> materialsList;

    public ItemTypeMaterializedEditor(PluginBuilderPanel parent) {
        super(Strings.ContentEditors.MATERIALIZED_ITEM_TYPES, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E, H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.infoField = helper.add(new JTextField(), "Description", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.materialsList = helper.add(new JList<>(), "Material Types", H_E, V_S, H_L, H_FLD * 10, GuiHelper.Align.ABOVE);
    }

    @Override
    public void populate(ContentObjectItemTypeMaterialized item) {
        this.statSetSelection.setSelectedItem(item.getStatSetName());
        this.scriptSelection.setSelectedItem(item.getScriptName());
        this.weightField.setText(item.getWeight() + "");
        this.infoField.setText(item.getDescription());

        List<String> materialTypes = item.getMaterialTypes();
        List<Integer> indices = new ArrayList<>();

        for (String materialType : materialTypes)
            for (int i = 0; i < this.materialsList.getModel().getSize(); i++)
                if (this.materialsList.getModel().getElementAt(i).equals(materialType))
                    indices.add(i);

        int[] i = new int[indices.size()];
        for (int x = 0; x < i.length; x++) {
            i[x] = indices.get(x);
        }

        this.materialsList.setSelectedIndices(i);
    }

    @Override
    public void onOpened(Module<ContentObjectItemTypeMaterialized> parent, PluginBuilderPanel panel) {
        Module statSetModule = panel.getModule("Stat Sets");
        List<String> statSets = statSetModule.getItemKeys();
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        List<String> scripts = this.getScripts("items", panel);
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        this.materialsList.setListData(materialTypes.toArray(new String[0]));
        this.materialsList.setSize(H_L, H_FLD * Math.max(1, materialTypes.size()));
    }

    @Override
    public ContentObjectItemTypeMaterialized createItem(String name, String owner) {
        String stat = (String) this.statSetSelection.getSelectedItem();
        String script = (String) this.scriptSelection.getSelectedItem();
        float weight = this.getFloat(this.weightField);
        String info = this.infoField.getText();
        List<String> materialTypes = this.materialsList.getSelectedValuesList();

        return new ContentObjectItemTypeMaterialized(name, owner, stat, script, weight, info, materialTypes);
    }

    public float getFloat(JTextField field) {
        float value;
        try {
            value = Float.parseFloat(field.getText());
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }

    @Override
    public ContentObjectItemTypeMaterialized getDefault() {
        return new ContentObjectItemTypeMaterialized("", "", "", "", 1F, "", new ArrayList<>());
    }

    @Override
    public ContentObjectItemTypeMaterialized consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String statSet = helper.getString("stats");
        String script = helper.getString("script");
        float weight = helper.getFloat("weight");
        String info = helper.getString("partInfo");
        List<String> materials = this.materialsList.getSelectedValuesList();

        return new ContentObjectItemTypeMaterialized(name, owner, statSet, script, weight, info, materials);
    }
}