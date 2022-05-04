package com.texnedo;

public class ConstrainedSubsequenceSum {
    public static void main(String[] args) {
        int[] data = {10,2,-10,5,20};
        System.out.println(constrainedSubsetSum(data, 2));
        int[] data1 = {-1, -2, -3};
        System.out.println(constrainedSubsetSum(data1, 1));
    }

    public static int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            int maxPrevious = 0;
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                if (maxPrevious < dp[j]) {
                    maxPrevious = dp[j];
                }
            }
            int dpSum = nums[i] + maxPrevious;
            dp[i] = dpSum;
        }
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (maxSum < dp[i]) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }
}
