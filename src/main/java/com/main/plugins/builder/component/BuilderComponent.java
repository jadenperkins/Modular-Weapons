package com.main.plugins.builder.component;

public interface BuilderComponent<T> {
    default BuilderLabel getAsLabel() {
        throw new UnsupportedOperationException();
    }
    default BuilderTextField getAsTextField() {
        throw new UnsupportedOperationException();
    }
    default BuilderTextArea getAsTextArea() {
        throw new UnsupportedOperationException();
    }
    default BuilderComboBox<T> getAsComboBox() {
        throw new UnsupportedOperationException();
    }
    default BuilderListBox<T> getAsListBox() {
        throw new UnsupportedOperationException();
    }
    default BuilderTable getAsTable() {
        throw new UnsupportedOperationException();
    }
    default BuilderPanel getAsPanel() {
        throw new UnsupportedOperationException();
    }
}
