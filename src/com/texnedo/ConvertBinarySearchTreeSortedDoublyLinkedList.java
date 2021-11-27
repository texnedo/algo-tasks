package com.texnedo;

import java.util.LinkedList;
import java.util.ListIterator;

public class ConvertBinarySearchTreeSortedDoublyLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        LinkedList<Node> result = new LinkedList<>();
        inOrderTraversal(root, result);
        ListIterator<Node> iterator = result.listIterator();
        Node current = null;
        Node next = null;
        Node previous = null;
        while (iterator.hasNext()) {
            if (current == null) {
                current = iterator.next();
                previous = result.getLast();
            }
            if (iterator.hasNext()) {
                next = iterator.next();
            } else {
                next = current;
            }
            current.right = next;
            current.left = previous;
            previous = current;
            current = next;
        }
        if (next != null) {
            next.left = previous;
            next.right = result.getFirst();
        }
        if (result.isEmpty()) {
            return null;
        }
        return result.getFirst();
    }

    void inOrderTraversal(Node root, LinkedList<Node> result) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, result);
        result.add(root);
        inOrderTraversal(root.right, result);
    }
}
