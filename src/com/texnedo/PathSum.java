package com.texnedo;

import com.texnedo.utils.TreeNode;

public class PathSum {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumInternal(root, sum) +
                pathSum(root.right, sum) +
                pathSum(root.left, sum);
    }

    private int pathSumInternal(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        count += pathSumInternal(root.left, sum - root.val);
        count += pathSumInternal(root.right, sum - root.val);
        return count;
    }
}
