package com.texnedo;

import com.texnedo.utils.TreeNode;

public class BinarySearchTreeGreaterSumTree {
    public static void main(String[] args) {
        BinarySearchTreeGreaterSumTree tree = new BinarySearchTreeGreaterSumTree();
        TreeNode node = TreeNode.parse(new Integer[] {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8});
        tree.bstToGst(node);
    }

    public TreeNode bstToGst(TreeNode root) {
        inorderTraverse(root, new int[] {0});
        return root;
    }

    private void inorderTraverse(TreeNode root, int[] cumulativeSum) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.right, cumulativeSum);
        cumulativeSum[0] += root.val;
        root.val = cumulativeSum[0];
        inorderTraverse(root.left, cumulativeSum);
    }
}
