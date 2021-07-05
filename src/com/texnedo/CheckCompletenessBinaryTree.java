package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class CheckCompletenessBinaryTree {
    public static void main(String[] args) {
        CheckCompletenessBinaryTree tree = new CheckCompletenessBinaryTree();
        Integer[] data = {1,2,3,4,5,6};
        TreeNode node = TreeNode.parse(data);
        System.out.println(tree.isCompleteTree(node));

        Integer[] data1 = {1,2,3,4,5,null,7};
        TreeNode node1 = TreeNode.parse(data1);
        System.out.println(tree.isCompleteTree(node1));

        Integer[] data2 = {1,2,3,5,null,7,8};
        TreeNode node2 = TreeNode.parse(data2);
        System.out.println(tree.isCompleteTree(node2));
    }

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        List<TreeNode> level = new LinkedList<>();
        level.add(root);
        int levelNumber = 0;
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new LinkedList<>();
            boolean hasEmptyNode = false;
            for (TreeNode node : level) {
                if (node == null) {
                    hasEmptyNode = true;
                    continue;
                }
                if (hasEmptyNode) {
                    return false;
                }
                nextLevel.add(node.left);
                nextLevel.add(node.right);
            }
            if (!nextLevel.isEmpty() && level.size() != (1 << levelNumber)) {
                return false;
            }
            level.clear();
            level.addAll(nextLevel);
            levelNumber++;
        }
        return true;
    }

}
