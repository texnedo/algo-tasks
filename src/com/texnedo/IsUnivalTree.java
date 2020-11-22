package com.texnedo;

import com.texnedo.utils.TreeNode;

public class IsUnivalTree {
    public static void main(String[] args) {
        IsUnivalTree tree = new IsUnivalTree();
        Integer[] data = {1,1,1,1,1,null,1};
        TreeNode node = TreeNode.parse(data);
        System.out.println(tree.isUnivalTree(node));

        Integer[] data1 = {2,2,2,5,2};
        TreeNode node1 = TreeNode.parse(data1);
        System.out.println(tree.isUnivalTree(node1));
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null) {
            if (root.left.val != root.val) {
                return false;
            }
            if (!isUnivalTree(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val != root.val) {
                return false;
            }
            if (!isUnivalTree(root.right)) {
                return false;
            }
        }
        return true;
    }
}
