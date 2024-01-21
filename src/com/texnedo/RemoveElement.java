package com.texnedo;

import java.util.Arrays;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == val) {
                return 0;
            }
            return 1;
        }
        int current = 0;
        int lastValid = nums.length - 1;
        while (current <= lastValid) {
            if (nums[current] == val) {
                while (nums[lastValid] == val && lastValid > current) {
                    lastValid--;
                }
                if (lastValid == current) {
                    if (lastValid == 0) {
                        return 0;
                    }
                    return lastValid;
                }
                swap(nums, current, lastValid);
            }
            current++;
        }
        return lastValid + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] data0 = {2, 2};
        System.out.println(removeElement.removeElement(data0, 5));
        System.out.println(Arrays.toString(data0));
        int[] data = {6, 3, 4, 5, 6, 7, 9, 6};
        System.out.println(removeElement.removeElement(data, 6));
        System.out.println(Arrays.toString(data));
        int[] data1 = {6, 6, 6, 6};
        System.out.println(removeElement.removeElement(data1, 6));
        System.out.println(Arrays.toString(data1));
        int[] data2 = {6, 6, 5, 6};
        System.out.println(removeElement.removeElement(data2, 6));
        System.out.println(Arrays.toString(data2));
    }
}
