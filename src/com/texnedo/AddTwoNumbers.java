package com.texnedo;

import com.texnedo.utils.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode result = null;
        int carry = 0;
        while(l1 != null || l2 != null){
            int sum;
            if (l1 != null && l2 != null) {
                sum = l1.val + l2.val + carry;
                if (sum <= 9) {
                    carry = 0;
                } else {
                    sum = sum - 10;
                    carry = 1;
                }
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1 != null) {
                sum = l1.val + carry;
                if (sum <= 9) {
                    carry = 0;
                } else {
                    sum = sum - 10;
                    carry = 1;
                }
                l1 = l1.next;
            }  else {
                sum = l2.val + carry;
                if (sum <= 9) {
                    carry = 0;
                } else {
                    sum = sum - 10;
                    carry = 1;
                }
                l2 = l2.next;
            }
            if (result == null) {
                result = new ListNode(sum);
                head = result;
            } else {
                result.next = new ListNode(sum);
                result = result.next;
            }
        }
        if (carry != 0) {
            result.next = new ListNode(carry);
        }
        return head;
    }
}
