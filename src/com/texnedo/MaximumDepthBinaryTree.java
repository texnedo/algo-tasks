package com.texnedo;

import com.texnedo.utils.TreeNode;

public class MaximumDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        return maxDepthInternal(root, 0);
    }

    public int maxDepthInternal(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }
        return Math.max(
                maxDepthInternal(node.left, depth + 1),
                maxDepthInternal(node.right, depth + 1)
        );
    }
}
