package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FindLargestValueEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new LinkedList<>();
        List<TreeNode> layer = new LinkedList<>();
        layer.add(root);
        List<TreeNode> nextLayer = new LinkedList<>();
        int maxInLayer = Integer.MIN_VALUE;
        while (!layer.isEmpty()) {
            for (TreeNode node : layer) {
                if (node.val > maxInLayer) {
                    maxInLayer = node.val;
                }
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            result.add(maxInLayer);
            maxInLayer = Integer.MIN_VALUE;
            layer.clear();
            layer.addAll(nextLayer);
            nextLayer.clear();
        }
        return result;
    }
}
