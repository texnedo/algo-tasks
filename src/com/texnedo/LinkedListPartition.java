package com.texnedo;

import com.texnedo.utils.ListNode;

import static com.texnedo.utils.ListNode.build;
import static com.texnedo.utils.ListNode.print;

public class LinkedListPartition {

    public static void main(String[] args) {
        LinkedListPartition partition = new LinkedListPartition();
        ListNode node = build("1->4->3->2->5->2");
        print(node);
        ListNode edited = partition.partition(node, 3);
        print(edited);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode next = head;
        ListNode previous = null;
        ListNode insertAfter = null;
        boolean foundPlaceToInsert = false;
        while(next != null) {
            if (!foundPlaceToInsert) {
                if (next.val >= x) {
                    insertAfter = previous;
                    foundPlaceToInsert = true;
                }
            } else {
                if (next.val < x) {
                    //remove node
                    previous.next = next.next;
                    //insert node
                    if (insertAfter == null) {
                        next.next = head;
                        head = next;
                    } else {
                        next.next = insertAfter.next;
                        insertAfter.next = next;
                    }
                    insertAfter = next;
                }
            }
            previous = next;
            next = next.next;
        }
        return head;
    }
}
