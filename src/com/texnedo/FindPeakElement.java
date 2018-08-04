package com.texnedo;

public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {2, 1};
        FindPeakElement element = new FindPeakElement();
        System.out.println(element.findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        return findPeakElementInternal(nums, 0, nums.length - 1);
    }

    int findPeakElementInternal(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        int middle = (end - start) / 2 + start;
        if (middle == start) {
            if (nums[middle] > nums[middle + 1]) {
                return middle;
            } else {
                return middle + 1;
            }
        } else if (middle == end) {
            if (nums[middle] > nums[middle - 1]) {
                return middle;
            } else {
                return middle - 1;
            }
        } else if (nums[middle] > nums[middle + 1] && nums[middle] > nums[middle - 1]) {
            return middle;
        } else if (nums[middle] < nums[middle + 1] && nums[middle] > nums[middle - 1]) {
            return findPeakElementInternal(nums, middle, end);
        } else {
            return findPeakElementInternal(nums, start, middle);
        }
    }
}
