package com.texnedo;

import com.texnedo.utils.TreeNode;

public class KthSmallestElementInBST {
    public static void main(String[] args) {
        KthSmallestElementInBST bst = new KthSmallestElementInBST();
        Integer[] data = {5, 2, 7, 1, 3, 6, 8};
        TreeNode node = TreeNode.parse(data);
        System.out.println(bst.kthSmallest(node, 1));
        System.out.println(bst.kthSmallest(node, 2));
        System.out.println(bst.kthSmallest(node, 3));
        System.out.println(bst.kthSmallest(node, 4));
        System.out.println(bst.kthSmallest(node, 5));
        System.out.println(bst.kthSmallest(node, 6));
        System.out.println(bst.kthSmallest(node, 7));
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        TreeNode node = kthSmallestInternal(root, k, new PassState());
        if (node == null) {
            throw new IllegalArgumentException();
        }
        return node.val;
    }

    private static class PassState {
        int countFromStart = 0;
    }

    public TreeNode kthSmallestInternal(TreeNode root, int k, PassState state) {
        if (root.left != null) {
            TreeNode node = kthSmallestInternal(root.left, k, state);
            if (node != null) {
                return node;
            }
        }
        state.countFromStart++;
        if (state.countFromStart == k) {
            return root;
        }
        if (root.right != null) {
            TreeNode node = kthSmallestInternal(root.right, k, state);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public int treeCountNodes(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int total = 1;
        if (root.left != null) {
            total += treeCountNodes(root.left);
        }
        if (root.right != null) {
            total += treeCountNodes(root.right);
        }
        return total;
    }
}
