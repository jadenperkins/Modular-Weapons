package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderLabel extends JLabel implements BuilderComponent {
    //Used only by base64 icon encoding
    private String dataString;
    @Override
    public BuilderLabel getAsLabel() {
        return this;
    }
    public String getDataString() {
        return dataString;
    }
    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
}
