package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceBinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> sorted = new ArrayList<>();
        inOrderTraverse(root, sorted);
        return buildBST(sorted, 0, sorted.size() - 1);
    }

    private TreeNode buildBST(List<TreeNode> sorted, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (end - start) / 2 + start;
        TreeNode root = sorted.get(middle);
        root.left = buildBST(sorted, start, middle - 1);
        root.right = buildBST(sorted, middle + 1, end);
        return root;
    }

    private void inOrderTraverse(TreeNode root, List<TreeNode> sorted) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left, sorted);
        sorted.add(root);
        inOrderTraverse(root.right, sorted);
    }
}
