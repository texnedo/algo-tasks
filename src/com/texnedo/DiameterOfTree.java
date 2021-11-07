package com.texnedo;

import com.texnedo.utils.TreeNode;

public class DiameterOfTree {
    public static void main(String[] args) {
        DiameterOfTree tree = new DiameterOfTree();
        Integer[] data = {1,2,3,4,5};
        System.out.println(tree.diameterOfBinaryTree(TreeNode.parse(data)));
        Integer[] data1 = {4,2,null,1,3};
        System.out.println(tree.diameterOfBinaryTree(TreeNode.parse(data1)));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 0;
        }
        int maxDiameterCurrent = getMaxDepth(root.left) + getMaxDepth(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        return Math.max(maxDiameterCurrent, Math.max(leftDiameter, rightDiameter));
    }

    private int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
