package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        BinaryTreePreorderTraversal traversal = new BinaryTreePreorderTraversal();
        Integer[] data = {5, 2, 7, 1, 3, 6, 8};
        TreeNode node = TreeNode.parse(data);
        TreeNode.print(node);
        System.out.println(traversal.preorderTraversal(node));
    }

    /**
     * Iterative solution must be provided
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.empty()) {
            TreeNode node = nodes.pop();
            result.add(node.val);
            if (node.right != null) {
                nodes.push(node.right);
            }
            if (node.left != null) {
                nodes.push(node.left);
            }
        }
        return result;
    }
}
