package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        List<Integer> result = new ArrayList<>(nums.length);
        ArrayList<Integer> seenBeforeSorted = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int indexSorted = Arrays.binarySearch(sorted, value);
            int indexSeen = Collections.binarySearch(seenBeforeSorted, value);
            if (indexSeen < 0) {
                //no such element
                indexSeen = -indexSeen - 1;
            }
            result.add(indexSorted - indexSeen);
            seenBeforeSorted.add(indexSeen, value);
        }
        return result;
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf count = new CountOfSmallerNumbersAfterSelf();
        int[] data = {5, 1, 3, 7, 4, 2, 6};
        int[] data1 = {-1, -1};
        System.out.println(count.countSmaller(data1));
        System.out.println(count.countSmaller(data));
    }
}
