package com.texnedo;

public class MissingNumber {
    public static void main(String[] args) {
        MissingNumber number = new MissingNumber();
        int[] data = {9,6,4,2,3,5,7,0,1};
        System.out.println(number.missingNumber(data));
    }

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            makeVisited(nums, getOriginal(nums[i]));
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i;
            }
        }
        return nums.length;
    }

    private int getOriginal(int i) {
        if (i >= 0) {
            return i;
        }
        return -i - 1;
    }

    private void makeVisited(int[] nums, int i) {
        if (i >= nums.length) {
            return;
        }
        nums[i] = -nums[i] - 1;
    }
}
