package com.texnedo;

import com.texnedo.utils.TreeNode;

public class SumOfLeftLeaves {

    public static void main(String[] args) {
        SumOfLeftLeaves sumOfLeftLeaves = new SumOfLeftLeaves();
        Integer[] data = {1,2,3,4,5};
        Integer[] data1 = {3,9,20,null,null,15,7};
        Integer[] data2 = {0,2,4,1,null,3,-1,5,1,null,6,null,8};
        TreeNode.print(TreeNode.parse(data2));
        System.out.println(sumOfLeftLeaves.sumOfLeftLeaves(TreeNode.parse(data)));
        System.out.println(sumOfLeftLeaves.sumOfLeftLeaves(TreeNode.parse(data1)));
        System.out.println(sumOfLeftLeaves.sumOfLeftLeaves(TreeNode.parse(data2)));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = sumOfLeftLeaves(root.right);
        if (root.left != null) {
            sum += sumOfLeftLeaves(root.left) + (root.left.left != null ? 0 : root.left.val);
        }
        return sum;
    }
}
