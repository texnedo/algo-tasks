package com.texnedo;

import java.util.*;

public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels labels = new PartitionLabels();
        System.out.println(labels.partitionLabels("ababcbacadefegdehijhklij"));
    }

    private static class RangeItem {
        final int[] range;
        final boolean isStart;
        final char letter;

        private RangeItem(int[] range, boolean isStart, char letter) {
            this.range = range;
            this.isStart = isStart;
            this.letter = letter;
        }

        private int getPosition() {
            return isStart ? range[0] : range[1];
        }
    }

    public List<Integer> partitionLabels(String S) {
        int[][] ranges = new int[26][];
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            int offset = current - 'a';
            int[] range = ranges[offset];
            if (range == null) {
                range = new int[2];
                ranges[offset] = range;
                range[0] = i;
                range[1] = i;
            } else {
                range[1] = i;
            }
        }
        ArrayList<RangeItem> rangeItems = new ArrayList<>(26 * 2);
        for (int i = 0; i < ranges.length; i++) {
            if (ranges[i] != null) {
                char current = (char)('a' + i);
                rangeItems.add(new RangeItem(ranges[i], true, current));
                rangeItems.add(new RangeItem(ranges[i], false, current));
            }
        }
        rangeItems.sort(Comparator.comparingInt(RangeItem::getPosition));
        Stack<RangeItem> rangeStack = new Stack<>();
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < rangeItems.size(); i++) {
            RangeItem current = rangeItems.get(i);
            if (rangeStack.empty()) {
                rangeStack.push(current);
            } else {
                RangeItem previous = rangeStack.peek();
                if (previous.isStart && !current.isStart) {
                    rangeStack.pop();
                    if (rangeStack.isEmpty()) {
                        result.add(current.range[1] - previous.range[0] + 1);
                    }
                } else {
                    rangeStack.push(current);
                }
            }
        }
        return result;
    }
}
