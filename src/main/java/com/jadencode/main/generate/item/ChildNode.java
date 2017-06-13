package com.jadencode.main.generate.item;

/**
 * Created by gtrpl on 6/13/2017.
 */
public class ChildNode<T> extends Node<T> {
    private final Node<T> parent;

    public ChildNode(T data, Node<T> parent) {
        super(data);
        this.parent = parent;
    }
    @Override
    public Node<T> getParent() {
        return parent;
    }
    @Override
    public Node<T> getRoot() {
        return parent.getRoot();
    }
}
