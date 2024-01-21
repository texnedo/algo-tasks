package com.texnedo;

import java.util.*;

public class KDiffPairs {
    public static void main(String[] args) {
        KDiffPairs pairs = new KDiffPairs();
        int[] data = {1, 2, 3, 4, 5};
        System.out.println(pairs.findPairs(data, 1));
    }

    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        if (k < 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> pairs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], ++count);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int opt1 = nums[i] + k;
            Integer count1 = map.get(opt1);
            if (count1 != null && (k != 0 || count1 > 1)) {
                pairs.put(Math.min(nums[i], opt1), Math.max(nums[i], opt1));
            }
            int opt2 = nums[i] - k;
            Integer count2 = map.get(opt2);
            if (count2 != null && (k != 0 || count2 > 1)) {
                pairs.put(Math.min(nums[i], opt2), Math.max(nums[i], opt2));
            }
        }
        return pairs.size();
    }
}
