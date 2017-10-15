package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderTextBox extends JTextField implements BuilderComponent {
    @Override
    public String stringValue() {
        return this.getText();
    }
    @Override
    public int intValue() {
        return 0;
    }
    @Override
    public double doubleValue() {
        return 0;
    }
    @Override
    public float floatValue() {
        return 0;
    }
    @Override
    public void onClick() {

    }
}
