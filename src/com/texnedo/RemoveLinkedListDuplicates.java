package com.texnedo;

import com.texnedo.utils.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RemoveLinkedListDuplicates {
    private static class NodePair {
        ListNode current;
        ListNode previous;
    }

    public static void main(String[] args) {
        String list = "1->1->2->1->3->3->2";
        //String list = "2->1->1->1";
        //String list = "1->1->1->2->2->2->1";
        RemoveLinkedListDuplicates duplicates = new RemoveLinkedListDuplicates();
        ListNode.print(duplicates.removeDuplicatesLeaveLast(ListNode.build(list)));
        //ListNode.print(duplicates.removeDuplicatesTwoPass(ListNode.build(list)));
    }

    /**
     * 1->1->2->1->3->3->2
     * 1->3->2
     * */
    ListNode removeDuplicatesLeaveLast(ListNode list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        ListNode previous = null;
        ListNode current = list;
        ListNode head = list;
        HashMap<Integer, NodePair> map = new HashMap<>();
        while (current != null) {
            NodePair pair = map.get(current.val);
            if (pair == null) {
                pair = new NodePair();
                map.put(current.val, pair);
            } else {
                if (head == pair.current) {
                    head = pair.current.next;
                } else {
                    pair.previous.next = pair.current.next;
                }
                if (previous == pair.current) {
                    previous = pair.previous;
                }
            }
            pair.current = current;
            pair.previous = previous;
            previous = current;
            current = current.next;
        }
        return head;
    }

    ListNode removeDuplicatesNoOrder(ListNode list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        ListNode previous = null;
        ListNode current = list;
        HashSet<Integer> set = new HashSet<>();
        while (current != null) {
            if (!set.contains(current.val)) {
                set.add(current.val);
                previous = current;
            } else {
                if (previous == null) {
                    throw new IllegalStateException();
                }
                previous.next = current.next;
            }
            current = current.next;
        }
        return list;
    }
}
