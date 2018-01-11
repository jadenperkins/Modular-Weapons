package com.main.plugins.builder.component;

import javax.swing.*;

public class BuilderTextArea extends JTextArea implements BuilderComponent {
    @Override
    public BuilderTextArea getAsTextArea() {
        return this;
    }
}
