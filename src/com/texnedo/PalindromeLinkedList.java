package com.texnedo;

import com.texnedo.utils.ListNode;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        PalindromeLinkedList list = new PalindromeLinkedList();
        ListNode node = ListNode.build("1->2->3->3->2->1");
        System.out.println(list.isPalindrome(node));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode end = null;
        int count = 0;
        while (fast != null) {
            slow = slow.next;
            end = fast;
            fast = fast.next;
            count++;
            if (fast == null) {
                break;
            }
            end = fast;
            fast = fast.next;
            count++;
        }
        System.out.println(count);
        ListNode reverse = slow;
        while (reverse != null) {
            ListNode next = reverse.next;
            if (next != null) {
                ListNode tmp = next.next;
                next.next = reverse;
                reverse = tmp;
            }
        }
        return true;
    }
}
