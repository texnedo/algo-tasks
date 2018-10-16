package com.texnedo;

import com.texnedo.utils.ListNode;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        MergeTwoSortedLists merge = new MergeTwoSortedLists();
        ListNode l1 = ListNode.build("1->2->4");
        ListNode l2 = ListNode.build("1->3->4");
        ListNode result = merge.mergeTwoLists(l1, l2);
        ListNode.print(result);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
           return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode left = l1;
        ListNode right = l2;
        ListNode head = null;
        ListNode current = null;
        while (true) {
            if (left == null) {
                current.next = right;
                break;
            }
            if (right == null) {
                current.next = left;
                break;
            }
            ListNode min;
            if (left.val < right.val) {
                min = left;
                left = left.next;
            } else {
                min = right;
                right = right.next;
            }
            if (current == null) {
                head = min;
            } else {
                current.next = min;
            }
            current = min;
        }
        return head;
    }
}
