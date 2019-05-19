package com.texnedo;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int firstMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > firstMax) {
                firstMax = nums[i];
            }
        }
        if (nums.length == 2) {
            return firstMax;
        }
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > secondMax && nums[i] < firstMax) {
                secondMax = nums[i];
            }
        }
        if (secondMax == Integer.MIN_VALUE) {
            return firstMax;
        }
        int thirdMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > thirdMax && nums[i] < secondMax) {
                thirdMax = nums[i];
            }
        }
        if (thirdMax == Integer.MIN_VALUE) {
            return firstMax;
        }
        return thirdMax;
    }
}
