package com.texnedo;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class DataStreamDisjointIntervals {
    public static void main(String[] args) {
        SummaryRanges intervals = new SummaryRanges();
        intervals.addNum(1);
        System.out.println(intervals.ranges);
        intervals.addNum(3);
        System.out.println(intervals.ranges);
        intervals.addNum(7);
        System.out.println(intervals.ranges);
        intervals.addNum(2);
        System.out.println(intervals.ranges);
        intervals.addNum(6);
        System.out.println(intervals.ranges);
    }

    static class Range {
        public int start;
        public int end;

        public Range(int start) {
            this.start = start;
            this.end = start;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static class SummaryRanges {
        private TreeMap<Integer, Range> ranges = new TreeMap<>();

        public SummaryRanges() {

        }

        public void addNum(int val) {
            if (ranges.containsKey(val)) {
                return;
            }
            boolean attached = false;
            final Map.Entry<Integer, Range> left = ranges.floorEntry(val);
            if (left != null) {
                if (left.getValue().end == val) {
                    return;
                }
                if (left.getValue().end == val - 1) {
                    left.getValue().end++;
                    attached = true;
                }
            }
            final Map.Entry<Integer, Range> right = ranges.ceilingEntry(val);
            if (right != null) {
                if (right.getValue().start == val) {
                    return;
                }
                if (right.getValue().start == val + 1) {
                    right.getValue().start--;
                    attached = true;
                }
            }
            if (right != null && left != null && left.getValue().end == right.getValue().start) {
                ranges.remove(left.getKey());
                right.getValue().start = left.getValue().start;
            }
            if (!attached) {
                ranges.put(val, new Range(val));
            }
        }

        public int[][] getIntervals() {
            int[][] data = new int[ranges.size()][2];
            int i = 0;
            for (Map.Entry<Integer, Range> item : ranges.entrySet()) {
                data[i][0] = item.getValue().start;
                data[i][1] = item.getValue().end;
                i++;
            }
            return data;
        }
    }
}
