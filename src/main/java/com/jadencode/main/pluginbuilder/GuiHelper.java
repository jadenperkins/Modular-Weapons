package com.jadencode.main.pluginbuilder;

import javax.swing.*;

/**
 * Created by JPERKI8 on 6/21/2016.
 */
public class GuiHelper {
    public enum Align {
        ABOVE(0, 0, 0, 1),
        BELOW(0, 1, 0, 0),
        LEFT(-1, 0, 0, 0),
        RIGHT(1, 0, 0, 0);

        public final int labelX;
        public final int labelY;
        public final int compX;
        public final int compY;

        Align(int labelX, int labelY, int compX, int compY) {
            this.labelX = labelX;
            this.labelY = labelY;
            this.compX = compX;
            this.compY = compY;
        }
    }

    public static GuiHelper above(JComponent parent) {
        return new GuiHelper(parent);
    }
    public static GuiHelper below(JComponent parent) {
        return new GuiHelper(parent, Align.BELOW);
    }
    public static GuiHelper left(JComponent parent) {
        return new GuiHelper(parent, Align.LEFT);
    }
    public static GuiHelper right(JComponent parent) {
        return new GuiHelper(parent, Align.RIGHT);
    }

    private final JComponent parent;
    private final Align defaultAlign;

    public GuiHelper(JComponent parent) {
        this(parent, Align.ABOVE);
    }
    public GuiHelper(JComponent parent, Align align) {
        this.parent = parent;
        this.defaultAlign = align;
    }

    //Creates a component and adds it to the parent component
    public <T extends JComponent> T add(T component, int x, int y, int width, int height) {
        component.setLocation(x, y);
        component.setSize(width, height);
        this.parent.add(component);
        return component;
    }
    //Creates a label with the default alignment and puts it on the parent component, then adds the component
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height) {
        return this.add(component, title, x, y, width, height, this.defaultAlign);
    }
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height, int spacing) {
        return this.add(component, title, x, y, width, height, spacing, this.defaultAlign);
    }
    /**Creates a label with the specified alignment and puts it on the parent, then add the component*/
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height, Align align) {
        return this.add(component, title, x, y, width, height, this.stringWidth(title), align);
    }
    /**Creates a label with the specified alignment and spacing, adds it to the parent, then adds the component*/
    public <T extends JComponent> T add(T component, String title, int x, int y, int width, int height, int spacing, Align align) {
        this.label(title, x, y, height, spacing + 8, align);
        return this.add(component, x + spacing * align.compX, y + 20 * align.compY, width, height);
    }



    /**Creates a scrolling component and adds it to the parent*/
    public <T extends JComponent> T addScrolling(T component, int x, int y, int width, int height) {
        this.add(new JScrollPane(component), x, y, width, height);
        return component;
    }
    public <T extends JComponent> T addScrolling(T component, String title, int x, int y, int width, int height) {
        return this.addScrolling(component, title, x, y, width, height, this.defaultAlign);
    }
    public <T extends JComponent> T addScrolling(T component, String title, int x, int y, int width, int height, int spacing) {
        return this.addScrolling(component, title, x, y, width, height, spacing, this.defaultAlign);
    }
    public <T extends JComponent> T addScrolling(T component, String title, int x, int y, int width, int height, Align align) {
        return this.addScrolling(component, title, x, y, width, height, this.stringWidth(title), align);
    }
    public <T extends JComponent> T addScrolling(T component, String title, int x, int y, int width, int height, int spacing, Align align) {
        this.label(title, x, y, height, spacing + 8, align);
        return this.addScrolling(component, x + spacing * align.compX, y + 20 * align.compY, width, height);
    }




    private int stringWidth(String s) {
        return this.parent.getFontMetrics(this.parent.getFont()).stringWidth(s);
    }
    private void label(String title, int x, int y, int height, int spacing, Align align) {
        JLabel label = new JLabel(title);
        label.setSize(spacing, 18);
        label.setLocation(x + spacing * align.labelX, y + height * align.labelY);
        this.parent.add(label);
    }
}