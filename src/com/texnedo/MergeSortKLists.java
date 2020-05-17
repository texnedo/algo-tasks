package com.texnedo;

import com.texnedo.utils.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeSortKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        PriorityQueue<ListNode> nodes = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        nodes.addAll(Arrays.asList(lists));
        while (!nodes.isEmpty()) {
            ListNode node = nodes.poll();
            if (node == null) {
                break;
            }
            if (result == null) {
                result = new ListNode(node.val);
            }
            if (node.next != null) {
                nodes.add(node);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);

        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);

        ListNode[] nodes = {l11, l12, l13, l21, l22, l23, l31, l32};
        MergeSortKLists merge = new MergeSortKLists();
        ListNode.print(merge.mergeKLists(nodes));
    }
}
