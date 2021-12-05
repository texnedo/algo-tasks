package com.texnedo;

public class MissingElementSortedArray {
    public static void main(String[] args) {
        MissingElementSortedArray array = new MissingElementSortedArray();
        int[] data1 = {1};
        System.out.println(array.missingElement(data1, 1));
        int[] data = {4,7,9,10};
        System.out.println(array.missingElement(data, 3));
    }

    public int missingElement(int[] nums, int k) {
        return missingElement(nums, k, 0, nums.length - 1);
    }

    private int countMissingElements(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int length = end - start + 1;
        int valueDiff = nums[end] - nums[start];
        int missingCount = valueDiff - length + 1;
        return missingCount;
    }

    private int missingElement(int[] nums, int k, int start, int end) {
        if (k <= 0) {
            return -1;
        }
        int missingCount = countMissingElements(nums, start, end);
        if (k > missingCount) {
            if (end == nums.length - 1) {
                int outerMissing = k - missingCount;
                return nums[end] + outerMissing;
            }
        } else {
            if (end - start == 1) {
                return nums[start] + k;
            }
            int middle = (end - start) / 2 + start;
            int leftMissingCount = countMissingElements(nums, start, middle);
            if (k <= leftMissingCount) {
                return missingElement(nums, k, start, middle);
            } else {
                return missingElement(nums, k - leftMissingCount, middle, end);
            }
        }
        return -1;
    }
}
