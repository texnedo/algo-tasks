package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.AbstractMap;
import java.util.Map;

public class LongestUnivaluePath {

    public static void main(String[] args) {
        LongestUnivaluePath path = new LongestUnivaluePath();
        Integer[] data = {5, 4, 5, 1, 1, null, 5};
        Integer[] data1 = {5, 4, 5, 1, 1};
        Integer[] data2 = {1, 4, 5, 4, 4, null, 5};
        System.out.println(path.longestUnivaluePath(TreeNode.parse(data)));
        System.out.println(path.longestUnivaluePath(TreeNode.parse(data1)));
        System.out.println(path.longestUnivaluePath(TreeNode.parse(data2)));
    }

    public int longestUnivaluePath(TreeNode root) {
        Map.Entry<Integer, Integer> result = longestUnivaluePathInternal(root);
        if (result == null) {
            return 0;
        }
        return result.getValue();
    }

    public Map.Entry<Integer, Integer> longestUnivaluePathInternal(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map.Entry<Integer, Integer> maxLeft = null;
        if (root.left != null) {
            maxLeft = longestUnivaluePathInternal(root.left);
            if (maxLeft != null && maxLeft.getKey() == root.val) {
                maxLeft = new AbstractMap.SimpleEntry<>(root.val, maxLeft.getValue() + 1);
            }
        }
        Map.Entry<Integer, Integer> maxRight = null;
        if (root.right != null) {
            maxRight = longestUnivaluePathInternal(root.right);
            if (maxRight != null && maxRight.getKey() == root.val) {
                maxRight = new AbstractMap.SimpleEntry<>(root.val, maxRight.getValue() + 1);
            }
        }
        if (maxLeft == null && maxRight == null) {
            return new AbstractMap.SimpleEntry<>(root.val, 0);
        }
        if (maxLeft == null) {
            return maxRight;
        }
        if (maxRight == null) {
            return maxLeft;
        }
        if (root.val == maxLeft.getKey() && root.val == maxRight.getKey()) {
            return new AbstractMap.SimpleEntry<>(root.val, maxLeft.getValue() + maxRight.getValue());
        }
        return Math.max(maxLeft.getValue(), maxRight.getValue()) == maxLeft.getValue()
                ? maxLeft : maxRight;
    }
}
