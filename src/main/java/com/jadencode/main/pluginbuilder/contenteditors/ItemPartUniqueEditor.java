package com.jadencode.main.pluginbuilder.contenteditors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.pluginbuilder.GuiHelper;
import com.jadencode.main.pluginbuilder.PluginBuilderPanel;
import com.jadencode.main.pluginbuilder.content.ContentObjectItemPartUnique;
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
public class ItemPartUniqueEditor extends ContentEditor<ContentObjectItemPartUnique> {

    private static final String[] QUALITY_LEVELS = {
            "COMMON", "UNCOMMON", "RARE", "EPIC", "LEGENDARY"
    };

    private final JTextField nameModField;
    private final JTextField partInfoField;
    private final JTextField weightField;
    private final JComboBox<String> partTypeSelection;
    private final JComboBox<String> statSetSelection;
    private final JComboBox<String> iconSelection;
    private final JComboBox<String> scriptSelection;
    private final JComboBox<String> qualitySelection;
    private final JTable jointsTable;

    public ItemPartUniqueEditor(PluginBuilderPanel parent) {
        super(Strings.ContentEditors.UNIQUE_ITEM_PARTS, parent);
        GuiHelper helper = GuiHelper.left(this);
        this.nameModField = helper.add(new JTextField(), "Name Modifier", H_S, V_E, H_L, H_FLD);
        this.partInfoField = helper.add(new JTextField(), "Part Info", H_S, V_E + H_FLD + V_PAD, H_L, H_FLD);
        this.weightField = helper.add(new JTextField(), "Weight", H_S, V_E + 2 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection = helper.add(new JComboBox<>(), "Part Type", H_S, V_E + 3 * (H_FLD + V_PAD), H_L, H_FLD);
        this.partTypeSelection.addActionListener(e -> this.checkJoints());
        this.statSetSelection = helper.add(new JComboBox<>(), "Stat Set", H_S, V_E + 4 * (H_FLD + V_PAD), H_L, H_FLD);
        this.iconSelection = helper.add(new JComboBox<>(), "Icon", H_S, V_E + 5 * (H_FLD + V_PAD), H_L, H_FLD);
        this.scriptSelection = helper.add(new JComboBox<>(), "Script", H_S, V_E + 6 * (H_FLD + V_PAD), H_L, H_FLD);
        this.qualitySelection = helper.add(new JComboBox<>(), "Quality Level", H_S, V_E + 7 * (H_FLD + V_PAD), H_L, H_FLD);
        this.jointsTable = helper.add(new JTable(), "Joints", H_E, V_S, H_L, H_NTR, GuiHelper.Align.ABOVE);
    }

    @Override
    public void onOpened(Module<ContentObjectItemPartUnique> parent, PluginBuilderPanel panel) {
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

        this.qualitySelection.setModel(new DefaultComboBoxModel<>(QUALITY_LEVELS));
    }

    @Override
    public void populate(ContentObjectItemPartUnique item) {
        this.nameModField.setText(item.getNameMod());
        this.partInfoField.setText(item.getPartInfo());
        this.weightField.setText(item.getWeight() + "");
        this.partTypeSelection.setSelectedItem(item.getPartType());
        this.statSetSelection.setSelectedItem(item.getStatSet());
        this.iconSelection.setSelectedItem(item.getIconName());
        this.qualitySelection.setSelectedItem(item.getQualityLevel());
        this.scriptSelection.setSelectedItem(item.getScript());

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

    private double getDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception ignored) {
        }
        return 0D;
    }

    @Override
    public ContentObjectItemPartUnique createItem(String name, String owner) {
        String nameMod = this.nameModField.getText();
        String partInfo = this.partInfoField.getText();
        float weight = this.getValue(this.weightField);
        String partType = (String) this.partTypeSelection.getSelectedItem();
        String statSet = (String) this.statSetSelection.getSelectedItem();
        String iconName = (String) this.iconSelection.getSelectedItem();
        String quality = (String) this.qualitySelection.getSelectedItem();
        String script = (String) this.scriptSelection.getSelectedItem();

        HashMap<String, Point.Double> joints = new HashMap<>();
        int rows = this.jointsTable.getRowCount();
        for (int i = 0; i < rows; i++) {
            String s = (String) this.jointsTable.getValueAt(i, 0);
            if (s != null && !s.isEmpty()) {
                double x = getDouble((String) this.jointsTable.getValueAt(i, 1));
                double y = getDouble((String) this.jointsTable.getValueAt(i, 2));
                joints.put(s, new Point.Double(x, y));
            }
        }

        return new ContentObjectItemPartUnique(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, quality, joints);
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
    public ContentObjectItemPartUnique getDefault() {
        return new ContentObjectItemPartUnique("", "", "", "", 0F, "", "", "", "", "", new HashMap<>());
    }

    @Override
    public ContentObjectItemPartUnique consume(String name, JsonObject json, String owner) {
        JsonHelper helper = new JsonHelper(json);
        String nameMod = helper.getString("nameMod");
        String partInfo = helper.getString("partInfo");
        float weight = helper.getFloat("weight", 1F);
        String partType = helper.getString("partType");
        String statSet = helper.getString("stats");
        String iconName = helper.getString("icon");
        String quality = helper.getString("quality");
        String script = helper.getString("script");
        HashMap<String, Point.Double> joints = new HashMap<>();
        JsonArray array = helper.getArray("joints");
        for (JsonElement jsonElement : array) {
            JsonObject obj = jsonElement.getAsJsonObject();
            JsonHelper h = new JsonHelper(obj);
            joints.put(h.getString("name"), new Point.Double(h.getDouble("x"), h.getDouble("y")));
        }

        return new ContentObjectItemPartUnique(name, owner, nameMod, partInfo, weight, partType, statSet, iconName, script, quality, joints);
    }
}