package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderPanel extends JPanel implements BuilderComponent {
    @Override
    public BuilderPanel getAsPanel() {
        return this;
    }
}
