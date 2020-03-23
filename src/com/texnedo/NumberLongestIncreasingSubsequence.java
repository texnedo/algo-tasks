package com.texnedo;

public class NumberLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int[] lengthEndingSubsequence = new int[nums.length];
        int[] countSubsequence = new int[nums.length];
        int[] maxItem = new int[nums.length];
        lengthEndingSubsequence[0] = 1;
        countSubsequence[0] = 1;
        maxItem[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                lengthEndingSubsequence[i] = lengthEndingSubsequence[i - 1] + 1;
                countSubsequence[i] = countSubsequence[i - 1];
            }
        }
        return 0;
    }
}
