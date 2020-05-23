package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.*;

public class LowestCommonAncestorBinaryTree {
    public static void main(String[] args) {
        LowestCommonAncestorBinaryTree tree = new LowestCommonAncestorBinaryTree();
        Integer[] data = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeNode.parse(data);
        LinkedList<TreeNode> path = new LinkedList<>();
        tree.runDFSWithPath(root, new TreeNode(7), path);
        System.out.println(path);
        System.out.println(tree.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1)));
        System.out.println(tree.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4)));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        LinkedList<TreeNode> pathFirst = new LinkedList<>();
        TreeNode resultFirst = runDFSWithPath(root, p, pathFirst);
        if (resultFirst == null) {
            return null;
        }
        LinkedList<TreeNode> pathSecond = new LinkedList<>();
        TreeNode resultSecond = runDFSWithPath(root, q, pathSecond);
        if (resultSecond == null) {
            return null;
        }
        if (pathFirst.isEmpty() || pathSecond.isEmpty()) {
            return null;
        }
        Iterator<TreeNode> first = pathFirst.iterator();
        Iterator<TreeNode> second = pathSecond.iterator();
        TreeNode firstPrevious = first.next();
        TreeNode secondPrevious = second.next();
        while (first.hasNext() && second.hasNext()) {
            TreeNode firstCurrent = first.next();
            TreeNode secondCurrent = second.next();
            if (firstCurrent.val != secondCurrent.val) {
                break;
            }
            firstPrevious = firstCurrent;
            secondPrevious = secondCurrent;
        }
        if (firstPrevious.val == secondPrevious.val) {
            return firstPrevious;
        }
        return null;
    }

    private TreeNode runDFSWithPath(TreeNode root, TreeNode node, LinkedList<TreeNode> path) {
        if (root == null) {
            return null;
        }
        if (root.val == node.val) {
            if (path != null) {
                path.addFirst(root);
            }
            return root;
        }
        TreeNode result = runDFSWithPath(root.left, node, path);
        if (result != null) {
            if (path != null) {
                path.addFirst(root);
            }
            return result;
        }
        result = runDFSWithPath(root.right, node, path);
        if (result != null) {
            if (path != null) {
                path.addFirst(root);
            }
        }
        return result;
    }

    private TreeNode runDFSToParent(TreeNode root, TreeNode node) {
        if (root == null) {
            return null;
        }
        if (root.val == node.val) {
            return root;
        }
        if (root.left != null && root.left.val == node.val) {
            return root;
        }
        if (root.right != null && root.right.val == node.val) {
            return root;
        }
        TreeNode result = runDFSToParent(root.left, node);
        if (result != null) {
            return result;
        }
        return runDFSToParent(root.right, node);
    }
}
