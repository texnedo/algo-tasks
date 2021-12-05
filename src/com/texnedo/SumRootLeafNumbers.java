package com.texnedo;

import com.texnedo.utils.TreeNode;

public class SumRootLeafNumbers {
    public int sumNumbers(TreeNode root) {
        int[] result = new int[1];
        traverseDFS(root, 0, result);
        return result[0];
    }

    private void traverseDFS(TreeNode root, int pathSum, int[] result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result[0] += pathSum * 10 + root.val;
            return;
        }
        int updatedPathSum = pathSum * 10 + root.val;
        traverseDFS(root.left, updatedPathSum, result);
        traverseDFS(root.right, updatedPathSum, result);
    }
}
