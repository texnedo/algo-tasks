package com.texnedo.utils;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static void print(ListNode head) {
        ListNode next = head;
        while(next != null) {
            System.out.print(next.val);
            next = next.next;
            if (next != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static ListNode build(String text) {
        return build(text, "->");
    }

    public static ListNode build(String text, String delimiter) {
        if (text == null || text.length() == 0) {
            return null;
        }
        final String[] tokens = text.split(delimiter);
        ListNode head = null;
        ListNode next = null;
        for (String token : tokens) {
            if (head == null) {
                head = new ListNode(Integer.parseInt(token));
                next = head;
            } else {
                next.next = new ListNode(Integer.parseInt(token));
                next = next.next;
            }
        }
        return head;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
