package com.texnedo;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RemoveAllAdjacentDuplicatesStringII {
    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesStringII remove = new RemoveAllAdjacentDuplicatesStringII();
        System.out.println(remove.removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(remove.removeDuplicates("deeedbbcccbdaa", 3));
    }

    static class Pair {
        final char item;
        int count;

        Pair(char item) {
            this.item = item;
            this.count = 1;
        }
    }

    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new Pair(current));
            } else {
                Pair last = stack.peek();
                if (last.item == current) {
                    last.count++;
                    if (last.count == k) {
                        stack.pop();
                    }
                } else {
                    stack.push(new Pair(current));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (Pair pair : stack) {
            for (int i = 0; i < pair.count; i++) {
                result.append(pair.item);
            }
        }
        return result.toString();
    }

    public String removeDuplicatesSlow(String s, int k) {
        if (k == 0) {
            return s;
        }
        if (s.length() < k) {
            return s;
        }
        int repeatCount = 1;
        char previous = s.charAt(0);
        List<int[]> toRemove = new LinkedList<>();
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == previous) {
                if (repeatCount == k) {
                    toRemove.add(new int[] {i - repeatCount, i});
                    repeatCount = 1;
                } else {
                    repeatCount++;
                }
            } else {
                if (repeatCount == k) {
                    toRemove.add(new int[] {i - repeatCount, i});
                }
                previous = current;
                repeatCount = 1;
            }
        }
        if (repeatCount == k) {
            toRemove.add(new int[] {s.length() - repeatCount, s.length()});
        }
        if (toRemove.isEmpty()) {
            return s;
        }
        StringBuilder result = new StringBuilder(s);
        int shift = 0;
        for (int[] part : toRemove) {
            result.replace(part[0] - shift, part[1] - shift, "");
            shift += k;
        }
        return removeDuplicates(result.toString(), k);
    }
}
