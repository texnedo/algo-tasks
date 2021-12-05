package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.LinkedList;

public class LowestCommonAncestorBinaryTree2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pParents = new LinkedList<>();
        if (!runDFS(root, p, pParents)) {
            return null;
        }
        LinkedList<TreeNode> qParents = new LinkedList<>();
        if (!runDFS(root, q, qParents)) {
            return null;
        }
        TreeNode lastEqual = null;
        while(!pParents.isEmpty() && !qParents.isEmpty()) {
            TreeNode pIterator = pParents.pollLast();
            TreeNode qIterator = qParents.pollLast();
            if (pIterator == qIterator) {
                lastEqual = pIterator;
            }
        }
        return lastEqual;
    }

    private boolean runDFS(TreeNode current, TreeNode target, LinkedList<TreeNode> path) {
        if (current == null) {
            return false;
        }
        if (current == target) {
            path.add(current);
            return true;
        }
        if (runDFS(current.left, target, path)) {
            path.add(current);
            return true;
        }
        if (runDFS(current.right, target, path)) {
            path.add(current);
            return true;
        }
        return false;
    }
}
