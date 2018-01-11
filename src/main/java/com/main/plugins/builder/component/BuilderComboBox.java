package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderComboBox<T> extends JComboBox<T> implements BuilderComponent {
    @Override
    public BuilderComboBox<T> getAsComboBox() {
        return this;
    }
}
