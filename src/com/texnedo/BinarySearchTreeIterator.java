package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        Integer[] data = {7, 3, 15, 1, 4, 9, 20};
        TreeNode root = TreeNode.parse(data);
        TreeNode.print(root);
        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static class BSTIterator {
        private final TreeNode root;
        private final LinkedList<TreeNode> orderQueue = new LinkedList<>();
        private final HashSet<TreeNode> visited = new HashSet<>();

        public BSTIterator(TreeNode root) {
            this.root = root;
            if (root != null) {
                orderQueue.add(root);
            }
        }

        /** @return the next smallest number */
        public int next() {
            if (orderQueue.isEmpty()) {
                throw new IllegalStateException();
            }
            while (true) {
                TreeNode lastNode = orderQueue.peek();
                if (lastNode == null) {
                    throw new IllegalStateException();
                }
                if (!visited.contains(lastNode) && addAllLeftChildren(lastNode)) {
                    TreeNode lastLeftNode = orderQueue.pop();
                    if (lastLeftNode == null) {
                        throw new IllegalStateException();
                    }
                    if (lastLeftNode.right != null) {
                        orderQueue.push(lastLeftNode.right);
                    }
                    return lastLeftNode.val;
                } else {
                    orderQueue.pop();
                    if (lastNode.right != null) {
                        orderQueue.push(lastNode.right);
                    }
                    return lastNode.val;
                }
            }
        }

        private boolean addAllLeftChildren(TreeNode node) {
            TreeNode current = node;
            boolean added = false;
            while (current.left != null) {
                visited.add(current);
                orderQueue.push(current.left);
                current = current.left;
                added = true;
            }
            return added;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !orderQueue.isEmpty();
        }
    }
}
