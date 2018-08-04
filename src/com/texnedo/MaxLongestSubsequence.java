package com.texnedo;

public class MaxLongestSubsequence {
    /**
     * Will not work for example with int [] arr = {10,9,2,5,3,4};
     * */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sequenceLength = 1;
            int lastInSequence = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[lastInSequence]) {
                    sequenceLength++;
                    lastInSequence = j;
                }
            }
            if (maxLength < sequenceLength) {
                maxLength = sequenceLength;
            }
        }
        return maxLength;
    }

    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return lengthOfLIS1Internal(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthOfLIS1Internal(int[] nums, int previous, int current) {
        if (current == nums.length) {
            return 0;
        }
        int useNext = 0;
        int notUseNext = 0;
        if (previous < 0 || nums[previous] < nums[current]) {
            useNext = 1 + lengthOfLIS1Internal(nums, current, current + 1);
        }
        notUseNext = lengthOfLIS1Internal(nums, previous, current + 1);
        return Math.max(useNext, notUseNext);
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int [] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxBefore = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxBefore = Math.max(maxBefore, dp[j]);
                }
            }
            dp[i] = maxBefore + 1;
        }
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            if (maxLength < dp[i]) {
                maxLength = dp[i];
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int [] arr = {10,9,2,5,3,4};
        MaxLongestSubsequence test = new MaxLongestSubsequence();
        System.out.println(test.lengthOfLIS2(arr));
    }
}
