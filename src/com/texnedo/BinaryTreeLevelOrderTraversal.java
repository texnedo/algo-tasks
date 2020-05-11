package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<TreeNode> layer = new LinkedList<>();
        List<TreeNode> nextLayer = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        layer.add(root);
        while (!layer.isEmpty()) {
            List<Integer> itemLayer = new ArrayList<>(layer.size());
            for (TreeNode node : layer) {
                itemLayer.add(node.val);
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            layer = nextLayer;
            nextLayer = new LinkedList<>();
            result.add(itemLayer);
        }
        return result;
    }
}
