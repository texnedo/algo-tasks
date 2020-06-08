package com.texnedo;

import java.util.Arrays;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int score = deleteAndEarn(nums, i);
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    private int deleteAndEarn(int[] nums, int index) {
        boolean[] deleted = new boolean[nums.length];
        deleted[index] = true;
        int deletedCount = 0;
        int score = nums[index];
        int nextIndexToCheck = index;
        for (int i = index + 1; i < nums.length; i++) {
            if (deleted[i]) {
                continue;
            }
            if (nums[i] == nums[index] + 1) {
                deleted[i] = true;
            } else {
                nextIndexToCheck = i;
                break;
            }
        }
        return 0;
    }
}
