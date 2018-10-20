package com.texnedo;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        int[] nums = {2,5,6,0,0,1,2};
        System.out.println(search.search(nums, 0));
    }

    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        return searchInternal(nums, target, 0, nums.length - 1);
    }

    public boolean search2(int[] nums, int target) {
        if (nums == null) {
            return false;
        }
        int index = searchInternal(nums, target, 0, nums.length - 1);
        return index >= 0;
    }

    public int searchInternal(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }
        int middle = start + (end - start) / 2;
        if (nums[middle] == target) {
            return middle;
        }
        boolean normal = nums[start] < nums[end];
        if (normal) {
            if (nums[middle] > target) {
                return searchInternal(nums, target, start, middle - 1);
            }
            return searchInternal(nums, target, middle + 1, end);
        }
        int foundLow = searchInternal(nums, target, start, middle - 1);
        if (foundLow != -1) {
            return foundLow;
        }
        return searchInternal(nums, target, middle + 1, end);
    }
}
