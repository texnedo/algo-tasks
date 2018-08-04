package com.texnedo;

import java.util.Arrays;

public class FindRange {
    public static void main(String[] args) {
        FindRange range = new FindRange();
        int[] nums = {0,0,1,1,1,2,2,3,3,3,4,4,4, 4 ,5,5, 6 ,6,6,8,10,10};
        //int[] nums = {8, 8, 8};
        int target = 4;
        System.out.println(Arrays.toString(range.searchRange(nums, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        if (nums != null && nums.length > 0) {
            start = searchFirstLess(nums, target, 0, nums.length - 1);
            if (start != -1) {
                end = searchFirstGreater(nums, target, start, nums.length - 1);
            }
        }
        return new int[]{start, end};
    }

    public int searchFirstLess(int[] nums, int target, int start, int end) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (start > end) {
            return -1;
        }
        if (nums[start] == target) {
            return start;
        }
        int middle = (end - start) / 2 + start;
        if (nums[middle] == target) {
            if (nums[middle - 1] < target) {
                return middle;
            } else if (nums[middle - 1] > target) {
                throw new IllegalArgumentException();
            } else {
                return searchFirstLess(nums, target, start, middle - 1);
            }
        } else if (nums[middle] < target) {
            return searchFirstLess(nums, target, middle + 1, end);
        } else {
            return searchFirstLess(nums, target, start, middle - 1);
        }
    }

    public int searchFirstGreater(int[] nums, int target, int start, int end) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (start > end) {
            return -1;
        }
        if (nums[end] == target) {
            return end;
        }
        int middle = (end - start) / 2 + start;
        if (nums[middle] == target) {
            if (nums[middle + 1] > target) {
                return middle;
            } else if (nums[middle + 1] < target) {
                throw new IllegalArgumentException();
            } else {
                return searchFirstGreater(nums, target, middle + 1, end);
            }
        } else if (nums[middle] < target) {
            return searchFirstGreater(nums, target, middle + 1, end);
        } else {
            return searchFirstGreater(nums, target, start, middle - 1);
        }
    }
}
