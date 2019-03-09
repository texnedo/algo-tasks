package com.texnedo;

import java.util.Arrays;

public class RemoveDuplicatesInPlace {

    public static void main(String[] args) {
        int[] array = {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3};
        RemoveDuplicatesInPlace removeDuplicatesInPlace = new RemoveDuplicatesInPlace();
        int length = removeDuplicatesInPlace.removeDuplicates(array);
        int[] result = Arrays.copyOf(array, length);
        System.out.println(Arrays.toString(result));
    }

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int start = -1;
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (start == -1) {
                    start = i + 1;
                }
            } else if (start != -1) {
                int changedLength = shrinkArray(nums, start, i - 1, length);
                int shift = length - changedLength;
                i -= shift;
                length = changedLength;
                int[] result = Arrays.copyOf(nums, length);
                System.out.println("iteration: " + Arrays.toString(result));
                start = -1;
            }
        }
        if (start != -1) {
            length = shrinkArray(nums, start, length - 1, length);
            int[] result = Arrays.copyOf(nums, length);
            System.out.println("iteration: " + Arrays.toString(result));
        }
        return length;
    }

    public int shrinkArray(int[] nums, int start, int end, int length) {
        if (start < 0 || start > end || length <= start || length <= end) {
            return length;
        }
        int removed = end - start + 1;
        for (int i = end + 1, j = start; i < length; i++, j++) {
            nums[j] = nums[i];
        }
        return length - removed;
    }
}
