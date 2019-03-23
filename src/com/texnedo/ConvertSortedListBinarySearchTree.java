package com.texnedo;

import com.texnedo.utils.ListNode;
import com.texnedo.utils.TreeNode;

public class ConvertSortedListBinarySearchTree {
    public static void main(String[] args) {
        ConvertSortedListBinarySearchTree tree = new ConvertSortedListBinarySearchTree();
        ListNode node = ListNode.build("-10,-3,0,5,9", ",");
        TreeNode treeNode = tree.sortedListToBST(node);
        TreeNode.print(treeNode);
    }

    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) {
            TreeNode node = new TreeNode();
            node.val = head.val;
            return node;
        }
        if (head.next == tail) {
            if (tail != null) {
                TreeNode root = new TreeNode();
                root.val = tail.val;
                TreeNode leaf = new TreeNode();
                leaf.val = head.val;
                root.left = leaf;
                return root;
            } else {
                TreeNode node = new TreeNode();
                node.val = head.val;
                return node;
            }
        }
        System.out.println(head + " " + tail);
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
            if (fast != null && fast.next == tail) {
                break;
            }
        }
        TreeNode center = new TreeNode();
        center.val = slow.val;
        center.left = sortedListToBST(head, slow);
        center.right = sortedListToBST(slow, tail);
        return center;
    }
}
