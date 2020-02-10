package com.texnedo;

import com.texnedo.utils.ListNode;

public class SortList {
    public static void main(String[] args) {
        SortList list = new SortList();
        ListNode node = ListNode.build("4->2->1->3");
        ListNode.print(list.sortList(node));
    }

    private static final MergeTwoSortedLists MERGE = new MergeTwoSortedLists();

    public ListNode sortList(ListNode head) {
        return sortListInternal(head, null);
    }

    public ListNode sortListInternal(ListNode head, ListNode tail) {
        System.out.println(head + " -> " + tail);
        if (head == null) {
            return null;
        }
        if (head == tail) {
            head.next = null;
            return head;
        }
        ListNode middle = findMiddle(head, tail);
        if (middle == null) {
            head.next = null;
            return head;
        }
        ListNode leftEnd = middle;
        ListNode rightStart = middle.next;
        ListNode left = sortListInternal(head, leftEnd);
        ListNode right = sortListInternal(rightStart, tail);
        return MERGE.mergeTwoLists(left, right);
    }

    private ListNode findMiddle(ListNode start, ListNode end) {
        ListNode fast = start;
        ListNode slow = start;
        while (fast != end && fast != null && slow != null) {
            fast = fast.next;
            if (fast != null && fast != end) {
                fast = fast.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        return slow;
    }
}
