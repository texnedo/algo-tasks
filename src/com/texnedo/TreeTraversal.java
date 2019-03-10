package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeTraversal {

    public static void main(String[] args) {
        TreeTraversal traversal = new TreeTraversal();
        Integer[] data = {5, 2, 7, 1, 3, 6, 8};
        TreeNode node = TreeNode.parse(data);
        TreeNode.print(node);
        System.out.println(traversal.inOrder(node));
        System.out.println(traversal.preOrder(node));
        System.out.println(traversal.postOrder(node));
    }

    public List<Integer> inOrder(TreeNode node) {
        LinkedList<Integer> result = new LinkedList<>();
        inOrderInternal(node, result);
        return result;
    }

    public void inOrderInternal(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        inOrderInternal(node.left, traversal);
        traversal.add(node.val);
        inOrderInternal(node.right, traversal);
    }

    public List<Integer> preOrder(TreeNode node) {
        LinkedList<Integer> result = new LinkedList<>();
        preOrderInternal(node, result);
        return result;
    }

    public void preOrderInternal(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        traversal.add(node.val);
        preOrderInternal(node.left, traversal);
        preOrderInternal(node.right, traversal);
    }

    public List<Integer> postOrder(TreeNode node) {
        LinkedList<Integer> result = new LinkedList<>();
        postOrderInternal(node, result);
        return result;
    }

    public void postOrderInternal(TreeNode node, List<Integer> traversal) {
        if (node == null) {
            return;
        }
        postOrderInternal(node.left, traversal);
        postOrderInternal(node.right, traversal);
        traversal.add(node.val);
    }
}
