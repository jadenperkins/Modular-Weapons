package com.jadencode.main.pluginbuilder;

import javax.swing.*;

/**
 * Created by JPERKI8 on 6/21/2016.
 */
public class GuiHelper {
    public enum Align {
        ABOVE(0, 0, 0, 1),
        BELOW(0, 1, 0, 0),
        LEFT(0, 0, 1, 0),
        RIGHT(1, 0, 0, 0);

        public final int offX;
        public final int offY;
        public final int moveX;
        public final int moveY;

        Align(int offX, int offY, int moveX, int moveY) {
            this.offX = offX;
            this.offY = offY;
            this.moveX = moveX;
            this.moveY = moveY;
        }
    }
    private final JComponent parent;

    public GuiHelper(JComponent parent) {
        this.parent = parent;
    }
    public <T extends JComponent> T add(T component, int x, int y, int width, int height) {
        component.setLocation(x, y);
        component.setSize(width, height);
        this.parent.add(component);
        return component;
    }
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height, Align align) {
        JLabel label = new JLabel(title);
        int stringLength = title.length() * 6;
        label.setSize(stringLength, 18);
        label.setLocation(x + (width + 4) * align.offX, y + height * align.offY);
        this.parent.add(label);

        return this.add(component, x + stringLength * align.moveX, y + 20 * align.moveY, width, height);
    }
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height) {
        return this.add(component, title, x, y, width, height, Align.ABOVE);
    }
    public <T extends JComponent> T addScrolling(T component, int x, int y, int w, int h) {
        JScrollPane pane = new JScrollPane(component);
        pane.setLocation(x, y);
        pane.setSize(w, h);
        this.parent.add(pane);
        return component;
    }
    public <T extends JComponent> T addScrolling(T component, String title, int x, int y, int w, int h) {

        return this.addScrolling(component, x, y + 20, w, h);
    }
}