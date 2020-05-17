package com.texnedo;

public class BinarySearchTreeInsert {
    static class Node {
        final int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int data) {
        Node node = new Node(data);
        if (root == null) {
            return node;
        }
        Node insertPoint = root;
        while (true) {
            if (insertPoint.data == data) {
                return root;
            }
            if (insertPoint.data > data) {
                if (insertPoint.left == null) {
                    insertPoint.left = node;
                    return root;
                }
                insertPoint = insertPoint.left;
            } else {
                if (insertPoint.right == null) {
                    insertPoint.right = node;
                    return root;
                }
                insertPoint = insertPoint.right;
            }
        }
    }
}
