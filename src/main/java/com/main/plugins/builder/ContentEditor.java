package com.main.plugins.builder;

import com.main.plugins.builder.component.BuilderComponent;
import com.main.plugins.modules.Module;
import com.main.plugins.pipeline.PipelineObject;
import com.main.plugins.pipeline.PipelineObjectScript;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class ContentEditor<P extends PipelineObject> extends JPanel {

    public static final int H_BTN = 40;
    public static final int H_FLD = 20;
    public static final int H_NTR = 16;
    public static final int V_PAD = 10;
    public static final int H_PAD = 20;

    public static final int H_S = 100;
    public static final int H_E = H_S + 200 + H_PAD;
    public static final int V_S = 10;
    public static final int V_E = V_S + H_FLD + 2 * H_BTN + 3 * V_PAD;

    public static final int H_L = H_E - H_S - H_PAD;
    public static final int V_L = V_E - V_S;

    private final JTextField nameField;
    private final JButton updateItem;
    private final JButton deleteItem;

    private final Map<String, BuilderComponent> components = new HashMap<>();

    private final Module<P> parentModule;

    public ContentEditor(Module<P> parentModule, PluginBuilderPanel panel) {
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocation(430, 10);
        this.setSize(1000, 700);

        this.parentModule = parentModule;

        GuiHelper helper = GuiHelper.left(this);

        //Components take up the area from (0, 0) to (300, 130)
        //Editors should place components outside of this range
        this.nameField = helper.add(new JTextField(), "Item Name", H_S, V_S, H_L, H_FLD);
        this.updateItem = helper.add(new JButton("Update Item"), H_S, V_S + H_FLD + V_PAD, H_L, H_BTN);
        this.deleteItem = helper.add(new JButton("Delete Item"), H_S, V_S + H_FLD + H_BTN + 2 * V_PAD, H_L, H_BTN);

        this.updateItem.addActionListener(e -> {
            if(panel.getActivePlugin() == null) {
                JOptionPane.showMessageDialog(null, "An active plugin must be named before adding content!");
                return;
            }
            String itemName = this.nameField.getText();
            if(itemName != null && !itemName.isEmpty()) {
                parentModule.addItem(itemName, this, panel.getActivePlugin());
                panel.updateCurrentObjects(itemName);
            }
        });
        this.deleteItem.addActionListener(e -> {
            String itemName = this.nameField.getText();
            parentModule.remove(itemName);
            panel.updateCurrentObjects(null);
        });

        this.parentModule.populatePanel(this);
    }

    public Module<P> getParentModule() {
        return parentModule;
    }

    public <B extends Component & BuilderComponent> void addComponent(String name, B component, int x, int y, int w, int h) {
        components.put(name, component);
        component.setLocation(x, y);
        component.setSize(w, h);
        this.add(component);
    }
    public BuilderComponent getComponent(String name) {
        return components.get(name);
    }
    public List<String> getScripts(String type, PluginBuilderPanel panel) {
        Module<? extends PipelineObject> scriptsModule = panel.getModule("Scripts");
        List<String> scripts = new ArrayList<>();
        List<String> itemKeys = scriptsModule.getItemKeys();
        for (String key : itemKeys) {
            String scriptType = ((PipelineObjectScript) scriptsModule.getItem(key)).getType();
            if(type.equals(scriptType)) scripts.add(key);
        }
        scripts.add("");
        return scripts;
    }

    @Override
    public String toString() {
        return getParentModule().toString();
    }

    public void setName(String name) {
        this.nameField.setText(name);
    }
}
