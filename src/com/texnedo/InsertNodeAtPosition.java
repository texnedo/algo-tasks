package com.texnedo;

import com.texnedo.utils.SinglyLinkedListNode;

public class InsertNodeAtPosition {

    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head,
                                                     int data,
                                                     int position) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        if (head == null) {
            return node;
        }
        if (position == 0) {
            node.next = head;
            return node;
        }
        int currentPosition = 0;
        SinglyLinkedListNode current = head;
        while (currentPosition < position - 1) {
            if (current.next == null) {
                break;
            }
            current = current.next;
            currentPosition++;
        }
        node.next = current.next;
        current.next = node;
        return head;
    }
}
