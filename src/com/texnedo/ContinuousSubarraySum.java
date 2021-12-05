package com.texnedo;

import java.util.Arrays;
import java.util.HashMap;

public class ContinuousSubarraySum {
    public static void main(String[] args) {
        ContinuousSubarraySum sum = new ContinuousSubarraySum();
        int[] data = {23,2,4,6,7};
        int[] data1 = {0, 0};
        System.out.println(sum.checkSubarraySum(data1, 1));
        System.out.println(sum.checkSubarraySum(data, 6));
        System.out.println(sum.checkSubarraySum(data, 234));
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums.length == 1) {
            return false;
        }
        HashMap<Integer, Integer> prefixSums = new HashMap<>();
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            prefixSum %= k;
            if (prefixSum == 0 && i > 0) {
                return true;
            }
            Integer existing = prefixSums.get(prefixSum);
            if (existing != null && i - existing > 1) {
                return true;
            }
            prefixSums.put(prefixSum, i);
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Integer[][] cache = new Integer[nums.length][nums.length];
        return checkSubarraySumInternal(nums, k, 0, 0, 0, cache);
    }

    public boolean checkSubarraySumInternal(int[] nums,
                                            int k,
                                            int sum,
                                            int start,
                                            int end,
                                            Integer[][] cache) {
        //System.out.println(String.format("%d_%d = %d", start, end, sum));
        int count = end - start + 1;
        if (sum % k == 0 && count >= 2) {
            return true;
        }
        if (start > end || end >= nums.length - 1) {
            return false;
        }
        if (start < nums.length - 1) {
            Integer sumPrev1 = cache[start + 1][end];
            if (sumPrev1 != null) {
                if (sumPrev1 + nums[start] % k == 0) {
                    return true;
                }
            }
        }
        if (end > 0) {
            Integer sumPrev2 = cache[start][end - 1];
            if (sumPrev2 != null) {
                if (sumPrev2 + nums[end] % k == 0) {
                    return true;
                }
            }
        }
        cache[start][end] = sum;
        if (checkSubarraySumInternal(nums, k, sum + nums[end], start, end + 1, cache)) {
            return true;
        }
        if (checkSubarraySumInternal(nums, k, nums[end], end, end + 1, cache)) {
            return true;
        }
        return false;
    }
}
