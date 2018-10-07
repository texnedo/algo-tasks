package com.texnedo;

import com.texnedo.utils.ListNode;

import static com.texnedo.utils.ListNode.build;
import static com.texnedo.utils.ListNode.print;

public class ReverseLinkedList2 {
    public static void main(String[] args) {
        ReverseLinkedList2 reverse = new ReverseLinkedList2();
        ListNode node = build("1->2->3->4->5");
        print(node);
        ListNode edited = reverse.reverseBetween(node, 2, 4);
        print(edited);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m > n || m <= 0) {
            throw new IllegalArgumentException();
        }
        if (m == n) {
            return head;
        }
        final int startIndex = m - 1;
        final int endIndex = n - 1;
        final boolean useMiddle = ((endIndex - startIndex) % 2) != 0;
        final int bufferLength = (endIndex - startIndex) / 2;
        final int middle = startIndex + bufferLength;
        final ListNode[] buffer = new ListNode[useMiddle ? bufferLength + 1 : bufferLength];
        ListNode current = head;
        int index = 0;
        int indexToSwap = buffer.length - 1;
        while (current != null) {
            if (index >= startIndex && (useMiddle ? (index <= middle) : (index < middle))) {
                final int offset = index - startIndex;
                buffer[offset] = current;
            } else if (index <= endIndex && (useMiddle ? (index >= middle) : (index > middle))) {
                final int tmp = current.val;
                current.val = buffer[indexToSwap].val;
                buffer[indexToSwap].val = tmp;
                indexToSwap--;
            }
            current = current.next;
            index++;
            if (index > endIndex) {
                break;
            }
        }
        return head;
    }
}
