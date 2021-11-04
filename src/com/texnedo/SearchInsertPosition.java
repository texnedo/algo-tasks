package com.texnedo;

public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition position = new SearchInsertPosition();
        int[] data = {1, 3, 5, 6};
        //System.out.println(position.searchInsert(data, 5));
        System.out.println(position.searchInsert(data, 4));
        System.out.println(position.searchInsert(data, 2));
        System.out.println(position.searchInsert(data, 0));
        System.out.println(position.searchInsert(data, 7));
    }

    /**
     * Input: nums = [1,3,5,6], target = 5
     * Input: nums = [1,3,5,6], target = 2
     * */
    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, target, 0, nums.length - 1);
    }

    private int searchInsert(int[] nums, int target, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        int mid = start + (end - start) / 2;
        int midValue = nums[mid];
        if (target == midValue) {
            return mid;
        }
        if (target < midValue) {
            if (start == mid) {
                return mid;
            }
            return searchInsert(nums, target, start, mid - 1);
        }
        if (end == mid) {
            return mid + 1;
        }
        return searchInsert(nums, target, mid + 1, end);
    }
}
