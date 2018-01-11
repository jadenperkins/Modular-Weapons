package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderTextField extends JTextField implements BuilderComponent {
    @Override
    public BuilderTextField getAsTextField() {
        return this;
    }
    public int intValue() {
        return (int) doubleValue();
    }
    public double doubleValue() {
        try {
            double value = Double.parseDouble(getText());
            return value;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public float floatValue() {
        return (float) doubleValue();
    }
}
