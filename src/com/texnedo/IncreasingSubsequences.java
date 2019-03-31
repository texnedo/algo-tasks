package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class IncreasingSubsequences {
    public static void main(String[] args) {
        int[] data = {4, 6, 7, 7};
        IncreasingSubsequences subsequences = new IncreasingSubsequences();
        System.out.println(subsequences.findSubsequences(data));
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> all = findSubsequencesInternal(nums, nums.length - 1);
        HashSet<List<Integer>> result = new HashSet<>(all.size());
        for (List<Integer> item : all) {
            if (item.size() > 1) {
                result.add(item);
            }
        }
        return new ArrayList<>(result);
    }

    private List<List<Integer>> findSubsequencesInternal(int[] nums, int maxIndex) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        if (maxIndex == 0) {
            return Collections.singletonList(Collections.singletonList(nums[0]));
        }
        List<List<Integer>> previous = findSubsequencesInternal(nums, maxIndex - 1);
        List<List<Integer>> next = new ArrayList<>(previous.size());
        next.addAll(previous);
        next.add(Collections.singletonList(nums[maxIndex]));
        for (List<Integer> item : previous) {
            if (item.get(item.size() - 1) <= nums[maxIndex]) {
                List<Integer> extended = new ArrayList<>(item);
                extended.add(nums[maxIndex]);
                next.add(extended);
            }
        }
        return next;
    }
}
