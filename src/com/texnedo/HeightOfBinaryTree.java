package com.texnedo;

public class HeightOfBinaryTree {
    static class Node {
        final int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static int height(Node root) {
        int result = maxNodeCount(root);
        if (result == 0) {
            return 0;
        }
        return result - 1;
    }

    private static int maxNodeCount(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxNodeCount(root.left) + 1, maxNodeCount(root.right) + 1);
    }
}
