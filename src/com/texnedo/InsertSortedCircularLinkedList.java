package com.texnedo;

import com.texnedo.utils.ListNode;

public class InsertSortedCircularLinkedList {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node iter = head;
        Node prev = null;
        boolean inserted = false;
        do {
            prev = iter;
            iter = iter.next;
            if (iter.val >= prev.val) {
                if (insertVal >= prev.val && insertVal <= iter.val) {
                    Node node = new Node(insertVal);
                    node.next = iter;
                    prev.next = node;
                    inserted = true;
                    break;
                }
            } else {
                if (insertVal >= prev.val) {
                    Node node = new Node(insertVal);
                    node.next = iter;
                    prev.next = node;
                    inserted = true;
                    break;
                }
            }
        } while (iter != head);
        if (!inserted) {
            Node node = new Node(insertVal);
            node.next = head;
            prev.next = node;
        }
        return head;
    }
}
