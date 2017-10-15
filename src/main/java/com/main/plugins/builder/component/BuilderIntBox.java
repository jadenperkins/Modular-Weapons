package com.main.plugins.builder.component;

public class BuilderIntBox extends BuilderTextBox {
    @Override
    public int intValue() {
        String value = stringValue();
        try {
            int i = Integer.parseInt(value);
            return i;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
