package com.texnedo;

import com.texnedo.utils.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ReverseLinkedList reverse = new ReverseLinkedList();
        ListNode node = ListNode.build("1->2->3->4->5");
        ListNode.print(node);
        ListNode edited = reverse.reverseList(node);
        ListNode.print(edited);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode currentNode = head;
        ListNode previousNode = null;
        while (currentNode != null) {
            ListNode next = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = next;
        }
        return previousNode;
    }
}
