package com.texnedo;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] data = {1, 2, 3, 4, 5, 6, 7};
        int[] data1 = {1, 2, 3, 4, 5, 6, 7};
        rotateArray.rotate(data, 4);
        System.out.println(Arrays.toString(data));
        rotateArray.rotateCycle(data1, 4);
        System.out.println(Arrays.toString(data1));
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            shiftRight(nums);
        }
    }

    public void rotateCycle(int[] nums, int k) {
        k = k % nums.length;
        int lastIndex = 0;
        int last = nums[lastIndex];
        for (int i = 0; i < nums.length; i++) {
            int updatedIndex = getUpdatedIndex(nums, k, lastIndex);
            int tmp = last;
            last = nums[updatedIndex];
            nums[updatedIndex] = tmp;
            lastIndex = updatedIndex;
        }
    }

    private int getUpdatedIndex(int[] nums, int k, int index) {
        int updated = index + k;
        if (updated < nums.length) {
            return updated;
        }
        return updated - nums.length;
    }

    private void shiftRight(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int tmp = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i + 1] = nums[i];
        }
        nums[0] = tmp;
    }
}
