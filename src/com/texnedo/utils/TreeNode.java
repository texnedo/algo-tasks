package com.texnedo.utils;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public static class TreeNodePtr {
        final TreeNode node;

        public TreeNodePtr(TreeNode node) {
            this.node = node;
        }
    }

    public static void main(String[] args) {
        Integer[] data1 = {3,9,20,null,null,15,7};
        TreeNode node = parse(data1);
        print(node);
    }

    public static void print(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNodePtr> level = new LinkedList<>();
        LinkedList<TreeNodePtr> nextLevel = new LinkedList<>();
        level.add(new TreeNodePtr(node));
        while (!level.isEmpty()) {
            while (!level.isEmpty()) {
                TreeNodePtr current = level.poll();
                if (current == null) {
                    break;
                }
                System.out.print(current.node == null ? "null" : current.node.val);
                System.out.print(" ");
                if (current.node != null) {
                    nextLevel.add(new TreeNodePtr(current.node.left));
                    nextLevel.add(new TreeNodePtr(current.node.right));
                }
            }
            level.addAll(nextLevel);
            nextLevel.clear();
            System.out.println();
        }
    }

    public static TreeNode parse(Integer[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return build(data, 0, null);
    }

    private static TreeNode build(Integer[] data, Integer index, TreeNode parent) {
        if (index == null || data[index] == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.val = data[index];
        node.parent = parent;
        node.left = build(data, getLeftChild(data, index), node);
        node.right = build(data, getRightChild(data, index), node);
        return node;
    }

    public static Integer getLeftChild(Integer[] data, int index) {
        int left = index * 2 + 1;
        if (left >= data.length || left < 0) {
            return null;
        }
        return left;
    }

    public static Integer getRightChild(Integer[] data, int index) {
        int right = index * 2 + 2;
        if (right >= data.length || right < 0) {
            return null;
        }
        return right;
    }

    public static Integer getParent(Integer[] data, int index) {
        if (index >= data.length || index < 0) {
            return null;
        }
        if (index == 0) {
            return null;
        }
        if (index % 2 == 0) {
            return (index - 2) / 2;
        } else {
            return (index - 1) / 2;
        }
    }

    public static Integer getRightMost(Integer[] data) {
        return data.length - 1;
    }

    public static Integer getLeftMost(Integer[] data) {
        if (data.length == 0) {
            return null;
        }
        Integer current = 0;
        Integer previous = null;
        while (current != null) {
            previous = current;
            current = getLeftChild(data, current);
        }
        return previous;
    }
}
