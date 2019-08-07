package com.texnedo;

import com.texnedo.utils.ListNode;

public class OddEvenLinkedList {
    public static void main(String[] args) {
        OddEvenLinkedList list = new OddEvenLinkedList();
        ListNode node = ListNode.build("1->2->3->4->5");
        ListNode.print(list.oddEvenList(node));
        System.out.println("---------------");
        ListNode node1 = ListNode.build("1->2");
        ListNode.print(list.oddEvenList(node1));
        System.out.println("---------------");
        ListNode node2 = ListNode.build("1");
        ListNode.print(list.oddEvenList(node2));
    }

    /**
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     *
     * Example 2:
     *
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     * */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode previous = head;
        ListNode iterator2 = head;
        ListNode insertPoint = head;
        while (iterator2 != null) {
            previous = iterator2;
            iterator2 = iterator2.next;
            if (iterator2 == null) {
                break;
            }

            //move to the next node
            previous = iterator2;
            iterator2 = iterator2.next;

            if (iterator2 == null) {
                break;
            }

            //remove from the current position
            previous.next = iterator2.next;

            //insert into the beginning
            iterator2.next = insertPoint.next;
            insertPoint.next = iterator2;

            //move insertion point to the next node
            insertPoint = insertPoint.next;

            //restore iterator position
            iterator2 = previous;

            ListNode.print(head);
        }
        return head;
    }
}
