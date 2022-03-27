package com.texnedo;

import com.texnedo.utils.TreeNode;

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isBalanced(root.right) && isBalanced(root.left);
    }

    private int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeHeight(root.left), treeHeight(root.right)) + 1;
    }
}
