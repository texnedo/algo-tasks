package com.texnedo;

import com.texnedo.utils.TreeNode;

public class LongestUnivaluePath2 {
    public static void main(String[] args) {
        LongestUnivaluePath2 path2 = new LongestUnivaluePath2();
        Integer[] data = {5,4,5,1,1,5};
        TreeNode root = TreeNode.parse(data);
        System.out.println(path2.longestUnivaluePath(root));

        Integer[] data1 = {1,4,5,4,4,5};
        TreeNode root1 = TreeNode.parse(data1);
        System.out.println(path2.longestUnivaluePath(root1));

        Integer[] data2 = {1,null,1,1,1,1,1,1};
        TreeNode root2 = TreeNode.parse(data2);
        System.out.println(path2.longestUnivaluePath(root2));
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, null, 0)[0];
    }

    //TODO - if you will use uphill recursion, we may skip storing previous node,
    // as we have current and both children
    private int[] dfs(TreeNode current,
                     TreeNode previous,
                     int currentLength) {
        if (current == null) {
            return new int[] {0, -1};
        }
        int nextLength = currentLength;
        if (previous != null && previous.val == current.val) {
            nextLength++;
        } else {
            nextLength = 0;
        }
        int[] leftMaxLength = dfs(current.left, current, nextLength);
        int[] rightMaxLength = dfs(current.right, current, nextLength);
        if (leftMaxLength[1] == rightMaxLength[1] && leftMaxLength[1] == current.val) {
            return new int[] {
                    Math.max(nextLength, leftMaxLength[0] + rightMaxLength[0]),
                    current.val
            };
        }
        return new int[] {
                Math.max(nextLength, Math.max(leftMaxLength[0], rightMaxLength[0])),
                current.val
        };
    }
}
