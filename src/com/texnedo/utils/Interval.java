package com.texnedo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Interval implements Comparable <Interval> {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }

    public static List<Interval> parse(String intervals) {
        if (intervals == null || intervals.length() == 0) {
            throw new IllegalArgumentException();
        }
        final Stack<Integer> borders = new Stack<>();
        final List<Interval> intervalList = new ArrayList<>(intervals.length() / 5);
        for (int i = 0; i < intervals.length(); i++) {
            if (intervals.charAt(i) == ']') {
                if (borders.empty()) {
                    throw new IllegalArgumentException();
                }
                final Integer start = borders.pop();
                if (borders.empty()) {
                    break;
                }
                if (i - start < 3) {
                    throw new IllegalArgumentException();
                }
                final String block = intervals.substring(start + 1, i);
                final String[] tokens = block.split(",");
                if (tokens.length != 2) {
                    throw new IllegalArgumentException();
                }
                final int left = Integer.parseInt(tokens[0].trim());
                final int right = Integer.parseInt(tokens[1].trim());
                if (right < left) {
                    throw new IllegalArgumentException();
                }
                intervalList.add(new Interval(left, right));

            } else if (intervals.charAt(i) == '[') {
                borders.push(i);
            }
        }
        if (!borders.empty()) {
            throw new IllegalArgumentException();
        }
        return intervalList;
    }

    @Override
    public String toString() {
        return "[" + start +
                ", " + end +
                ']';
    }

    @Override
    public int compareTo(Interval o) {
        return Integer.compare(start, o.start);
    }
}
