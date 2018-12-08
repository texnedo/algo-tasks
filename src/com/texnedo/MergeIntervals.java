package com.texnedo;


import com.texnedo.utils.Interval;

import java.util.*;

class MergeIntervals {

    static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval l, Interval r) {
            return Integer.compare(l.start, r.start);
        }
    }

    public static void main(String[] arr) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(4, 5));
        list.add(new Interval(1, 4));
        System.out.println(merge(list));
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        intervals.sort(new IntervalComparator());
        ListIterator<Interval> iterator = intervals.listIterator();
        LinkedList<Interval> result = new LinkedList<>();
        while(iterator.hasNext()) {
            if (result.size() == 0) {
                result.addLast(iterator.next());
            } else {
                Interval current = iterator.next();
                Interval last = result.peekLast();
                if (current.start > last.end) {
                    result.addLast(current);
                    continue;
                }
                if (current.end <= last.end) {
                    continue;
                }
                last.end = current.end;
            }
        }
        return result;
    }
}
