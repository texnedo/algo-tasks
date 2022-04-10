package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            TreeNode node = new TreeNode();
            node.val = nums[start];
            return node;
        }
        int maxValue = Integer.MIN_VALUE;
        int maxValueIndex = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] >= maxValue) {
                maxValue = nums[i];
                maxValueIndex = i;
            }
        }
        TreeNode node = new TreeNode();
        node.val = maxValue;
        node.left = constructMaximumBinaryTree(nums, start, maxValueIndex - 1);
        node.right = constructMaximumBinaryTree(nums, maxValueIndex + 1, end);
        return node;
    }
}
