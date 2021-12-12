package com.texnedo;

public class MonotonicArray {
    public boolean isMonotonic(int[] nums) {
        if (nums == null) {
            return false;
        }
        if (nums.length == 0) {
            return true;
        }
        Boolean increasing = null;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > nums[i - 1]) {
                if (increasing == null) {
                    increasing = true;
                } else if (!increasing) {
                    return false;
                }
            } else {
                if (increasing == null) {
                    increasing = false;
                } else if (increasing) {
                    return false;
                }
            }
        }
        return true;
    }
}
