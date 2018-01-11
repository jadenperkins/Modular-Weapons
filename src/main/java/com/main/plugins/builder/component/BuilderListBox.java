package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderListBox<T> extends JList<T> implements BuilderComponent<T> {
    @Override
    public BuilderListBox<T> getAsListBox() {
        return this;
    }
}
