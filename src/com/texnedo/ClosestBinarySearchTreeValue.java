package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        List<Integer> ordered = new ArrayList<>();
        inOrderTraverse(root, ordered);
        double minDiff = Double.MAX_VALUE;
        int minValue = 0;
        for (Integer val : ordered) {
            double diff = Math.abs(val - target);
            if (diff < minDiff) {
                minDiff = diff;
                minValue = val;
            }
        }
        return minValue;
    }

    private void inOrderTraverse(TreeNode root, List<Integer> ordered) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left, ordered);
        ordered.add(root.val);
        inOrderTraverse(root.right, ordered);
    }
}
