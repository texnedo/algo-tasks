package com.texnedo;

import com.texnedo.utils.TreeNode;

public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (L == R && L != root.val) {
            return 0;
        }
        int sum = 0;
        if (root.val >= L && root.val <= R) {
            sum += root.val;
            sum += rangeSumBST(root.left, L, R);
            sum += rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            sum += rangeSumBST(root.left, L, R);
        } else {
            sum += rangeSumBST(root.right, L, R);
        }
        return sum;
    }
}
