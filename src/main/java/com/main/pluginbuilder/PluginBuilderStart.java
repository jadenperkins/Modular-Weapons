package com.main.pluginbuilder;

import javax.swing.*;

/**
 * Created by gtrpl on 6/18/2016.
 */
public class PluginBuilderStart {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Plugin Creator");
        frame.setSize(1500, 800);
        frame.setContentPane(new PluginBuilderPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}