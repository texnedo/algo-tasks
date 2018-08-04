package com.texnedo;

import java.util.HashMap;
import java.util.Map;

public class LongestLengthOfSequence {
    public static void main(String[] args) {
        int[] data = {100,4,200,1,3,2, 101, 102, 103, 104, 105};
        System.out.println(longestConsecutive(data));
    }

    static class Node {
        Node next;
        Node previous;
        int value;
    }

    public static int longestConsecutive(int[] nums) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Node node = nodeMap.get(nums[i]);
            if (node == null) {
                node = new Node();
                node.value = nums[i];
                nodeMap.put(nums[i], node);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            Node current = nodeMap.get(nums[i]);
            if (current == null) {
                throw new IllegalArgumentException();
            }
            Node previous = nodeMap.get(nums[i] - 1);
            Node next = nodeMap.get(nums[i] + 1);
            current.next = next;
            current.previous = previous;
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            Node current = nodeMap.get(nums[i]);
            if (current == null) {
                throw new IllegalArgumentException();
            }
            int length = traceLength(current);
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    public static int traceLength(Node node) {
        if (node == null) {
            return 0;
        }
        int length = 1;
        Node left = node.previous;
        Node right = node.next;
        while(left != null) {
            left = left.previous;
            length++;
        }
        while(right != null) {
            right = right.next;
            length++;
        }
        return length;
    }
}
