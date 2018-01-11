package com.main.plugins.builder.component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BuilderTable extends JTable implements BuilderComponent {

    public BuilderTable() {

    }
    public BuilderTable(int rows, int cols) {
        this.setModel(new DefaultTableModel(rows, cols));
    }
    @Override
    public BuilderTable getAsTable() {
        return this;
    }
}
