package com.jadencode.main.generate.item;

import com.jadencode.main.generate.item.instance.ItemPart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by gtrpl on 6/29/2016.
 */
public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children = new ArrayList<>();

    public Node(T val) {
        this.data = val;
    }
    public Node<T> addNode(T val) {
        return this.add(new Node<T>(val));
    }
    public Node<T> add(Node<T> node) {
        node.parent = this;
        this.children.add(node);
        return node;
    }
    public List<Node<T>> getChildren() {
        return this.children;
    }
    public T getData() {
        return this.data;
    }
    public Node<T> getParent() {
        return this.parent;
    }
    public static <T> Node<T> tree(T root, List<T> items, BiPredicate<T, T> comparison) {
        items = new ArrayList<T>(items);
        if(items.get(0) != root) {
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
            if(used.contains(item)) continue;

            if(comparison.test(currentItem, item)) {
                newItems.remove(item);
                newItems.add(0, item);
                root.add(tree(newItems, comparison, used));
            }
        }
        return root;
    }
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", parent=" + parent +
                ", children=" + children.size() +
                '}';
    }
}