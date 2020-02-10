package com.texnedo;

import com.texnedo.utils.TreeNode;

public class ConvertSortedArrayBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedArrayBinarySearchTree tree = new ConvertSortedArrayBinarySearchTree();
        int[] data = {-10,-3,0,5,9};
        TreeNode.print(tree.sortedArrayToBST(data));
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBSTInternal(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTInternal(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int middle = start + (end - start) / 2;
        TreeNode middleNode = new TreeNode(nums[middle]);
        middleNode.left = sortedArrayToBSTInternal(nums, start, middle - 1);
        middleNode.right = sortedArrayToBSTInternal(nums, middle + 1, end);
        return middleNode;
    }
}
