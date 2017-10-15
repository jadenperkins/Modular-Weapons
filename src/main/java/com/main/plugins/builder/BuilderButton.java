package com.main.plugins.builder;

import com.main.plugins.builder.component.BuilderComponent;

import javax.swing.*;

public class BuilderButton extends JButton implements BuilderComponent {
    private final Runnable eventHandler;

    public BuilderButton(Runnable eventHandler) {
        this.eventHandler = eventHandler;
    }
    @Override
    public String stringValue() {
        return null;
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
        eventHandler.run();
    }
}
