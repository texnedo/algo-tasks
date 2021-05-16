package com.texnedo;

import java.util.PriorityQueue;

public class CanConvertStringInKMoves {
    public static void main(String[] args) {
        CanConvertStringInKMoves moves = new CanConvertStringInKMoves();
        System.out.println(moves.canConvertString("input", "ouput", 9));
        System.out.println(moves.canConvertString("abc", "bcd", 10));
        System.out.println(moves.canConvertString("aab", "bbb", 27));
        System.out.println(moves.canConvertString("iqssxdlb", "dyuqrwyr", 40));
        System.out.println(moves.canConvertString("qsxkjbfz", "xyfirptk", 73));
        System.out.println(moves.canConvertString("mygdwuntwkoc", "btydmdiatnhx", 48));
    }

    private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

    private static final class ItemStatus implements Comparable<ItemStatus> {
        int distance;
        final int index;

        private ItemStatus(int distance, int index) {
            this.distance = distance;
            this.index = index;
        }

        @Override
        public int compareTo(ItemStatus o) {
            return Integer.compare(distance, o.distance);
        }
    }

    public boolean canConvertString(String s, String t, int k) {
        if (s == null || t == null || k < 0) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        PriorityQueue<ItemStatus> ordered = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            int leftIndex = s.charAt(i) - 'a';
            int rightIndex = t.charAt(i) - 'a';
            int distance = rightIndex - leftIndex;
            if (distance == 0) {
                continue;
            }
            if (distance < 0) {
                distance = ALPHABET_SIZE + distance;
            }
            if (distance > k) {
                return false;
            }
            ordered.offer(new ItemStatus(distance, i));
        }
        while (!ordered.isEmpty()) {
            ItemStatus item = ordered.poll();
            if (item.distance > k) {
                return false;
            }
            if (ordered.isEmpty()) {
                return true;
            }
            ItemStatus next = ordered.peek();
            if (next.distance == item.distance) {
                ordered.poll();
                next.distance += ALPHABET_SIZE;
                ordered.offer(next);
            }
        }
        return true;
    }
}
