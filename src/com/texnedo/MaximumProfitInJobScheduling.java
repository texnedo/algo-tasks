package com.texnedo;


import java.util.Arrays;

public class MaximumProfitInJobScheduling {
    private static class Interval implements Comparable<Interval> {
        private int start;
        private int end;
        private int profit;

        private Interval(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(start, o.start);
        }
    }

    public static void main(String[] args) {
//        int[] start = {1,2,3,3};
//        int[] end = {3,4,5,6};
//        int[] profit = {50,10,40,70};
        int[] start = {1,2,3,4,6};
        int[] end = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        MaximumProfitInJobScheduling scheduling = new MaximumProfitInJobScheduling();
        System.out.println(scheduling.jobScheduling(start, end, profit));
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Interval[] intervals = new Interval[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            intervals[i] = new Interval(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(intervals);
        Integer[] computed = new Integer[intervals.length];
        return jobSchedulingInternal(intervals, computed, 0);
    }

    private int jobSchedulingInternal(Interval[] intervals, Integer[] computed, int startIndex) {
        if (computed[startIndex] != null) {
            return computed[startIndex];
        }
        System.out.println("Start index = " + startIndex);
        Interval current = intervals[startIndex];
        if (startIndex == intervals.length - 1) {
            computed[startIndex] = current.profit;
            return current.profit;
        }
        int index = Arrays.binarySearch(
                intervals,
                startIndex + 1,
                intervals.length,
                new Interval(current.end, 0, 0)
        );
        if (index < 0) {
            index = -index - 1;
        }
        if (index >= intervals.length) {
            computed[startIndex] = current.profit;
            return current.profit;
        }
        int maxProfit = Integer.MIN_VALUE;
        for (int i = index; i < intervals.length; i++) {
            int profit = jobSchedulingInternal(intervals, computed, i) + current.profit;
            if (maxProfit < profit) {
                maxProfit = profit;
            }
        }
        computed[startIndex] = maxProfit;
        return maxProfit;
    }
}
