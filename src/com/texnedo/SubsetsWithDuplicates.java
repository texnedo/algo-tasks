package com.texnedo;

import java.util.*;

public class SubsetsWithDuplicates {
    public static void main(String[] args) {
        SubsetsWithDuplicates subsets = new SubsetsWithDuplicates();
        int[] nums = {1, 2, 2};
        System.out.println(subsets.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            return Collections.singletonList(Collections.emptyList());
        }
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = counts.get(nums[i]);
            if (count == null) {
                count = 0;
            }
            count++;
            counts.put(nums[i], count);
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(Collections.emptyList());
        for (Map.Entry<Integer, Integer> pair : counts.entrySet()) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> set = result.get(i);
                List<Integer> superSet = new ArrayList<>(set);
                for (int j = 0; j < pair.getValue(); j++) {
                    superSet.add(pair.getKey());
                    result.add(new ArrayList<>(superSet));
                }
            }
        }
        return result;
    }

}
