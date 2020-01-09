package com.texnedo.utils;

import java.util.LinkedList;

public class BaseTreeNode <T> {
    public T val;
    public BaseTreeNode left;
    public BaseTreeNode right;

    public static class TreeNodePtr {
        final BaseTreeNode node;

        public TreeNodePtr(BaseTreeNode node) {
            this.node = node;
        }
    }

    public static void print(BaseTreeNode node) {
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
}
