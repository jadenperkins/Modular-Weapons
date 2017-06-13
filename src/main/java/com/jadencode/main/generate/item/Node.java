package com.jadencode.main.generate.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;

/**
 * Created by gtrpl on 6/29/2016.
 */
public class Node<T> {
    private final T data;
    private final List<Node<T>> children = new ArrayList<>();

    public Node(T val) {
        this.data = val;
    }

    public static <T> Node<T> tree(T root, List<T> items, BiPredicate<T, T> comparison) {
        items = new ArrayList<>(items);
        if (items.get(0) != root) {
            items.remove(root);
            items.add(0, root);
        }
        return tree(items, comparison, new HashSet<>());
    }

    private static <T> Node<T> tree(List<T> items, BiPredicate<T, T> comparison, Set<T> used) {
        T currentItem = items.get(0);
        Node<T> root = new Node<>(currentItem);
        List<T> newItems = new ArrayList<>(items);
        newItems.remove(currentItem);
        used.add(currentItem);

        for (T item : items) {
            if (used.contains(item)) continue;

            if (comparison.test(currentItem, item)) {
                newItems.remove(item);
                newItems.add(0, item);
                root.addNode(tree(newItems, comparison, used));
            }
        }
        return root;
    }
    public Node<T> addNode(Node<T> node) {
        this.getChildren().add(node);
        return node;
    }
    public Node<T> addNodeMe(Node<T> node) {
        this.addNode(node);
        return this;
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getParent() {
        return this;
    }
    public Node<T> getRoot() {
        return this;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data.toString() +
                ", children=" + children.size() +
                '}';
    }
}