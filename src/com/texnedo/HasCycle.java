package com.texnedo;

import com.texnedo.utils.SinglyLinkedListNode;

public class HasCycle {
    static boolean hasCycle(SinglyLinkedListNode head) {
        if (head == null) {
            return false;
        }
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
