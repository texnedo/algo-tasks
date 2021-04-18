package com.texnedo;

import com.texnedo.utils.TreeNode;

public class DeleteNodeInBST {
    public static void main(String[] args) {
        Integer[] data = {5,3,6,2,4,null,7};
        TreeNode node = TreeNode.parse(data);
        TreeNode.print(node);
        DeleteNodeInBST bst = new DeleteNodeInBST();
        TreeNode updated = bst.deleteNode(node, 3);
        TreeNode.print(updated);
    }

    private TreeNode findBiggestNode(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.right == null) {
                return current;
            }
            current = current.right;
        }
        return null;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode current = root;
        TreeNode previous = null;
        while (current != null) {
            if (key == current.val) {
                break;
            }
            previous = current;
            if (key > current.val) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null) {
            return root;
        }
        if (current.left == null && current.right == null) {
            if (current == root) {
                return null;
            }
            if (previous.left == current) {
                previous.left = null;
            } else {
                previous.right = null;
            }
            return root;
        } else if (current.left != null) {
            TreeNode newRoot = root;
            if (current == root) {
                newRoot = current.left;
            }
            if (previous != null) {
                if (previous.left == current) {
                    previous.left = current.left;
                } else {
                    previous.right = current.left;
                }
            }
            if (current.right != null) {
                TreeNode biggestNode = findBiggestNode(current.left);
                if (biggestNode == null) {
                    throw new IllegalStateException();
                }
                biggestNode.right = current.right;
            }
            return newRoot;
        } else {
            TreeNode newRoot = root;
            if (current == root) {
                newRoot = current.right;
            }
            if (previous != null) {
                if (previous.left == current) {
                    previous.left = current.right;
                } else {
                    previous.right = current.right;
                }
            }
            return newRoot;
        }
    }
}
