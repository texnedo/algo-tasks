package com.texnedo;

public class FindMinimumRotatedSortedArray {
    /**
     * original
     * 1 2 3 4 5 6 7 8 9
     *
     * exit condition = middle
     * 6 7 8 9 1 2 3 4 5
     *
     * lookup in left half
     * 7 8 9 1 2 3 4 5 6
     *
     * lookup in right half
     * 4 5 6 7 8 9 1 2 3
     *
     * */
    public static void main(String[] args) {
        FindMinimumRotatedSortedArray min = new FindMinimumRotatedSortedArray();
        int[] data = {1, 0};
        System.out.println(min.findMin(data));

        int[] data1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(min.findMin(data1));

        int[] data2 = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        System.out.println(min.findMin(data2));

        int[] data3 = {4, 5, 6, 7, 8, 9, 1, 2, 3};
        System.out.println(min.findMin(data3));
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        int middle = start + (end - start) / 2;
        if (middle > start && middle < end &&
                nums[middle] <= nums[middle - 1] && nums[middle] <= nums[middle + 1]) {
            return nums[middle];
        }
        if (nums[middle] < nums[start] && nums[middle] < nums[end]) {
            return findMin(nums, start, middle - 1);
        }
        return findMin(nums, middle + 1, end);
    }
}
