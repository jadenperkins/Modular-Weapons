package com.main.plugins.builder.component;

public class BuilderDecimalBox extends BuilderTextBox {
    @Override
    public int intValue() {
        return (int) doubleValue();
    }
    @Override
    public double doubleValue() {
        try {
            return Double.parseDouble(stringValue());
        } catch (NumberFormatException e) {
            return 0D;
        }
    }
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }
}
