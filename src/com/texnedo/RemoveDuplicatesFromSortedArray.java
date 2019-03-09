package com.texnedo;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] array = {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3};
        RemoveDuplicatesFromSortedArray duplicates = new RemoveDuplicatesFromSortedArray();
        int length = duplicates.removeDuplicates(array);
        int[] result = Arrays.copyOf(array, length);
        System.out.println(Arrays.toString(result));
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int indexToInsert = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[indexToInsert] = nums[i];
                indexToInsert++;
            }
        }
        return indexToInsert;
    }
}
