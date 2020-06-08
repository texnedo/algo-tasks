package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;

public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        RemoveCoveredIntervals intervals = new RemoveCoveredIntervals();
        int[][] data = {{1,2},{1,4},{3,4}};
        intervals.removeCoveredIntervals(data);
        System.out.println(intervals.removeCoveredIntervals(data));
    }

    public int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }
        if (intervals.length == 0 || intervals.length == 1) {
            return intervals.length;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = Integer.compare(o1[0], o2[0]);
                if (result == 0) {
                    return -Integer.compare(o1[1], o2[1]);
                }
                return result;
            }
        });
        int count = intervals.length;
        int previousToCheck = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (isCovered(intervals[previousToCheck], intervals[i])) {
                count--;
            } else {
                previousToCheck = i;
            }
        }
        return count;
    }

    private boolean isCovered(int[] o1, int[] o2) {
        if (o1[0] <= o2[0] && o1[1] >= o2[1]) {
            return true;
        }
        return false;
    }
}
