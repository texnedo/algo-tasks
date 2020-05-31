package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.Map;
import java.util.Stack;

public class DeepestLeavesSum {
    public static void main(String[] args) {
        DeepestLeavesSum sum = new DeepestLeavesSum();
        Integer[] data = {1,2,3,4,5,null,6,7,null,null,null,null, null, null,8};
        TreeNode root = TreeNode.parse(data);
        System.out.println(sum.deepestLeavesSum(root));
    }

    private static class TreeNodeIndex {
        private final TreeNode node;
        private final int level;

        private TreeNodeIndex(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public int deepestLeavesSum(TreeNode root) {
        Stack<TreeNodeIndex> leafs = new Stack<>();
        runDFS(root, 0, leafs);
        int sum = 0;
        for (TreeNodeIndex item : leafs) {
            sum += item.node.val;
        }
        return sum;
    }

    public void runDFS(TreeNode root, int level, Stack<TreeNodeIndex> leafs) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            boolean addThisLeaf = true;
            while (!leafs.empty()) {
                TreeNodeIndex previous = leafs.peek();
                if (previous.level < level) {
                    leafs.pop();
                } else if (previous.level > level){
                    addThisLeaf = false;
                    break;
                } else {
                    break;
                }
            }
            if (addThisLeaf) {
                leafs.push(new TreeNodeIndex(root, level));
            }
        } else {
            runDFS(root.left, level + 1, leafs);
            runDFS(root.right, level + 1, leafs);
        }
    }
}
