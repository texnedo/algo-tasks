package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class CarPooling {
    public static void main(String[] args) {
        int[][] data = {{2,1,5},{3,3,7}};
        CarPooling carPooling = new CarPooling();
        System.out.println(carPooling.carPooling(data, 5));
        int[][] data1 = {{2,1,5},{3,5,7}};
        System.out.println(carPooling.carPooling(data1, 3));
        int[][] data2 = {{3,2,7},{3,7,9},{8,3,9}};
        System.out.println(carPooling.carPooling(data2, 11));
        int[][] data3 = {{9,3,4},{9,1,7},{4,2,4},{7,4,5}};
        System.out.println(carPooling.carPooling(data3, 23));
    }

    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || capacity < 0 || (trips.length > 0  && capacity == 0)) {
            throw new IllegalArgumentException();
        }
        Range[] ranges = new Range[trips.length];
        for (int i = 0; i < trips.length; i++) {
            ranges[i] = new Range(trips[i]);
        }
        Arrays.sort(ranges, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        int maxTotalCount = 0;
        int currentCount = 0;
        LinkedList<Range> pool = new LinkedList<>();
        for (int i = 0; i < ranges.length; i++) {
            Range current = ranges[i];
            if (pool.isEmpty()) {
                pool.addLast(current);
                currentCount = current.count;
                if (currentCount > maxTotalCount) {
                    maxTotalCount = currentCount;
                    if (maxTotalCount > capacity) {
                        return false;
                    }
                }
            } else {
                ListIterator<Range> iterator = pool.listIterator();
                while (!pool.isEmpty() && iterator.hasNext()) {
                    Range left = iterator.next();
                    if (left.end <= current.start) {
                        currentCount -= left.count;
                        iterator.remove();
                    }
                }
                pool.addLast(current);
                currentCount += current.count;
                if (currentCount > maxTotalCount) {
                    maxTotalCount = currentCount;
                    if (maxTotalCount > capacity) {
                        return false;
                    }
                }
            }
        }
        if (maxTotalCount > capacity) {
            return false;
        }
        return true;
    }

    private static class Range {
        final int count;
        final int start;
        final int end;

        private Range(int[] data) {
            if (data == null || data.length != 3) {
                throw new IllegalArgumentException();
            }
            if (data[0] < 0 || data[1] > data[2]) {
                throw new IllegalArgumentException();
            }
            this.count = data[0];
            this.start = data[1];
            this.end = data[2];
        }
    }
}
