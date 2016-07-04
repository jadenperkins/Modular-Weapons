package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemPartMaterialized;
import com.jadencode.main.pluginbuilder.content.ContentObjectPartType;
import com.jadencode.main.pluginbuilder.modules.Module;
import com.jadencode.main.util.JsonHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ItemPartMaterializedEditor extends ContentEditor<ContentObjectItemPartMaterialized> {

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JComboBox<String> partTypeSelection;
    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> iconSelection;
    private final JComboBox<String> scriptSelection;
    private final JList<String> materialsList;
    private final JTable jointsTable;

    public ItemPartMaterializedEditor(Module module, PluginBuilderPanel parent) {
        super(module, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.nameModField = helper.add(new JTextField(), "Name Modifier", H_S, V_E, H_L, H_FLD);
        this.partInfoField = helper.add(new JTextField(), "Part Info", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection = helper.add(new JComboBox<>(), "Part Type", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection.addActionListener(e -> this.checkJoints());
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E + 5 * (H_FLD + V_PAD), H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + 6 * (H_FLD + V_PAD), H_L, H_FLD);
        this.materialsList = helper.add(new JList<>(), "Material Types", H_E, V_S, H_L, H_FLD * 10, GuiHelper.Align.ABOVE);
        this.jointsTable = helper.add(new JTable(), "Joints", H_E + H_L + H_PAD, V_S, H_L, H_NTR, GuiHelper.Align.ABOVE);
    }

    @Override
    public void onOpened(Module<ContentObjectItemPartMaterialized> parent, PluginBuilderPanel panel) {
        Module partTypesModule = panel.getModule("Part Types");
        List<String> partTypes = partTypesModule.getItemKeys();
        this.partTypeSelection.setModel(new DefaultComboBoxModel<>(partTypes.toArray(new String[0])));

        Module statSetsModule = panel.getModule("Stat Sets");
        List<String> statSets = new ArrayList<>();
        statSets.add("");
        statSets.addAll(statSetsModule.getItemKeys());
        this.statSetSelection.setModel(new DefaultComboBoxModel<>(statSets.toArray(new String[0])));

        Module iconsModule = panel.getModule("Icons");
        List<String> icons = new ArrayList<>();
        icons.add("");
        icons.addAll(iconsModule.getItemKeys());
        this.iconSelection.setModel(new DefaultComboBoxModel<>(icons.toArray(new String[0])));

        Module scriptsModule = panel.getModule("Scripts");
        List<String> scripts = new ArrayList<>();
        scripts.add("");
        scripts.addAll(scriptsModule.getItemKeys());
        this.scriptSelection.setModel(new DefaultComboBoxModel<>(scripts.toArray(new String[0])));

        Module materialTypesModule = panel.getModule("Material Types");
        List<String> materialTypes = materialTypesModule.getItemKeys();
        this.materialsList.setListData(materialTypes.toArray(new String[0]));
        this.materialsList.setSize(H_L, H_FLD * Math.max(1, materialTypes.size()));
    }

    @Override
    public void populate(ContentObjectItemPartMaterialized item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeSelection.setSelectedItem(item.getPartType());
        this.statSetSelection.setSelectedItem(item.getStatSet());
        this.iconSelection.setSelectedItem(item.getIconName());
        this.scriptSelection.setSelectedItem(item.getScript());

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

        this.checkJoints();

        Module partTypes = this.getPluginBuilderPanel().getModule("Part Types");
        ContentObjectPartType type = (ContentObjectPartType) partTypes.getItem((String) this.partTypeSelection.getSelectedItem());

        if (type != null) {
            int size = type.getJoints().size();
            if (size > 0) {
                this.jointsTable.setModel(new DefaultTableModel(size, 3));
                HashMap<String, Point.Double> itemJoints = item.getJoints();
                for (int row = 0; row < type.getJoints().size(); row++) {
                    String name = type.getJoints().get(row);
                    this.jointsTable.setValueAt(name, row, 0);
                    if (itemJoints.get(name) != null) {
                        Point2D value = itemJoints.get(name);
                        this.jointsTable.setValueAt(value.getX(), row, 1);
                        this.jointsTable.setValueAt(value.getY(), row, 2);
                    }
                }
            }
        }
    }

    public void checkJoints() {
        Module partTypes = this.getPluginBuilderPanel().getModule("Part Types");
        ContentObjectPartType type = (ContentObjectPartType) partTypes.getItem((String) this.partTypeSelection.getSelectedItem());
        if (type != null) {
            this.jointsTable.setSize(H_L, H_NTR * Math.max(1, type.getJoints().size()));
            this.jointsTable.setModel(new DefaultTableModel(type.getJoints().size(), 3));
            List<String> joints = type.getJoints();
            for (int row = 0; row < joints.size(); row++) {
                this.jointsTable.setValueAt(joints.get(row), row, 0);
                this.jointsTable.setValueAt(0, row, 1);
                this.jointsTable.setValueAt(0, row, 2);
            }
        }
    }

    private double getDouble(Object s) {
        double value = 0;
        if (s instanceof Double) {
            value = (Double) s;
        } else {
            if (s instanceof String) {
                try {
                    value = Double.parseDouble((String) s);
                } catch (Exception e) {
                }
            }
        }
        return value;
    }

    @Override
    public ContentObjectItemPartMaterialized createItem(String name, String owner) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = (String) this.partTypeSelection.getSelectedItem();
        String statSet = (String) this.statSetSelection.getSelectedItem();
        String iconName = (String) this.iconSelection.getSelectedItem();
        String script = (String) this.scriptSelection.getSelectedItem();
        List<String> materialTypes = this.materialsList.getSelectedValuesList();

        HashMap<String, Point.Double> joints = new HashMap<>();
        int rows = this.jointsTable.getRowCount();
        for (int i = 0; i < rows; i++) {
            String s = (String) this.jointsTable.getValueAt(i, 0);
            if (s != null && !s.isEmpty()) {
                double x = getDouble(this.jointsTable.getValueAt(i, 1));
                double y = getDouble(this.jointsTable.getValueAt(i, 2));
                joints.put(s, new Point.Double(x, y));
            }
        }

        return new ContentObjectItemPartMaterialized(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, materialTypes, joints);
    }

    private float getValue(JTextField field) {
        float value;
        try {
            value = Float.parseFloat(field.getText());
        } catch (Exception e) {
            value = 0F;
        }
        return value;
    }

    @Override
    public ContentObjectItemPartMaterialized getDefault() {
        return new ContentObjectItemPartMaterialized("", "", "", "", 0F, "", "", "", "", new ArrayList<>(), new HashMap<>());
    }

    @Override
    public ContentObjectItemPartMaterialized consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String nameMod = helper.getString("nameMod");
        String partInfo = helper.getString("partInfo");
        float weight = helper.getFloat("weight", 1F);
        String partType = helper.getString("partType");
        String statSet = helper.getString("stats");
        String iconName = helper.getString("icon");
        String script = helper.getString("script");
        List<String> materials = JsonHelper.fromArray(helper.getArray("materials"));
        HashMap<String, Point.Double> joints = new HashMap<>();
        JsonArray array = helper.getArray("joints");
        for (JsonElement jsonElement : array) {
            JsonObject obj = jsonElement.getAsJsonObject();
            JsonHelper h = new JsonHelper(obj);
            joints.put(h.getString("name"), new Point.Double(h.getDouble("x"), h.getDouble("y")));
        }

        return new ContentObjectItemPartMaterialized(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, materials, joints);
    }
}