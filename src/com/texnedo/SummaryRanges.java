package com.texnedo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges ranges = new SummaryRanges();
        int[] data = {0,1,2,4,5,7};
        System.out.println(ranges.summaryRanges(data));
        int[] data1 = {-3,-2,0,1,2,4,5,7};
        System.out.println(ranges.summaryRanges(data1));
        int[] data2 = {1};
        System.out.println(ranges.summaryRanges(data2));
        int[] data3 = {1,2};
        System.out.println(ranges.summaryRanges(data3));
        int[] data4 = {4,3,2,1,-1,-2,-3};
        System.out.println(ranges.summaryRanges(data4));
    }

    /**
     * Input:  [0,1,2,4,5,7]
     * Output: ["0->2","4->5","7"]
     * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
     *
     * array may contain negative integers
     * */
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<String> result = new LinkedList<>();
        Integer start = null;
        Integer end = null;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (i > 0) {
                int previous = nums[i - 1];
                if (Math.abs(previous - current) != 1) {
                    if (end != null) {
                        result.add(String.format(Locale.US, "%d->%d", start, end));
                    } else {
                        result.add(Integer.toString(start));
                    }
                    start = null;
                    end = null;
                }
            }
            if (start == null) {
                start = current;
            } else {
                end = current;
            }
        }
        if (end != null) {
            result.add(String.format(Locale.US, "%d->%d", start, end));
        } else {
            result.add(Integer.toString(start));
        }
        return result;
    }
}
