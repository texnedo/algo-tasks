package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1, 2, 3, 4};
        System.out.println(subsets.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        return subsetsInternal(nums, nums.length);
    }

    public List<List<Integer>> subsetsInternal(int[] nums, int length) {
        if (nums == null) {
            return null;
        }
        if (length == 0) {
            return Collections.singletonList(Collections.emptyList());
        }
        List<List<Integer>> result = subsetsInternal(nums, length - 1);
        List<List<Integer>> merged = new ArrayList<>(result);
        for (List<Integer> set : result) {
            List<Integer> superSet = new ArrayList<>(set);
            superSet.add(nums[length - 1]);
            merged.add(superSet);
        }
        return merged;
    }
}
