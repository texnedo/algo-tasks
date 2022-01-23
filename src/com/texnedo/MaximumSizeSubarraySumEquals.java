package com.texnedo;

import java.util.HashMap;

public class MaximumSizeSubarraySumEquals {
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> indeces = new HashMap<>();
        int prefixSum = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) {
                maxLength = i + 1;
            }
            Integer startIndex = indeces.get(prefixSum - k);
            if (startIndex != null) {
                int length = i - startIndex;
                if (length > maxLength) {
                    maxLength = length;
                }
            }
            if (!indeces.containsKey(prefixSum)) {
                indeces.put(prefixSum, i);
            }
        }
        return maxLength;
    }
}
