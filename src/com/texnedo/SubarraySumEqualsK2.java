package com.texnedo;

public class SubarraySumEqualsK2 {
    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
        int count = 0;
        for (int i = 0; i < sum.length; i++) {
            for (int j = i + 1; j < sum.length; j++) {
                int currentSum = sum[j] - sum[i];
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
