package com.texnedo;

public class SingleElementInSortedArray {

    public static void main(String[] args) {
        SingleElementInSortedArray single = new SingleElementInSortedArray();
        int[] data = {1,1,2,3,3,4,4,8,8};
        //int[] data = {3,3,7,7,10,11,11};
        //int[] data = {1,1,2};
        //int[] data = {1,2,2};
        //int[] data = {1, 1, 2, 2, 4, 4, 5, 5, 9};
        System.out.println(single.singleNonDuplicate(data));
    }
    /**
     * Note: Your solution should run in O(log n) time and O(1) space.
     * Input: [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * */
    public int singleNonDuplicate(int[] nums) {
        return singleNonDuplicateInternal(nums, 0, nums.length - 1);
    }

    public int singleNonDuplicateInternal(int[] nums, int start, int end) {
        int middle = start + (end - start) / 2;
        int secondIndex = getSameValueIndex(nums, middle);
        if (secondIndex == -1) {
            return nums[middle];
        }
        int left = Math.min(secondIndex, middle);
        int right = Math.max(secondIndex, middle);
        int leftSize = left;
        int rightSize = nums.length - right - 1;
        if (leftSize % 2 == 0) {
            return singleNonDuplicateInternal(nums, middle + 1, end);
        }
        return singleNonDuplicateInternal(nums, start, middle - 1);
    }

    private int getSameValueIndex(int[] nums, int current) {
        if (current < nums.length - 1) {
            int next = current + 1;
            if (nums[current] == nums[next]) {
                return next;
            }
        }
        if (current > 0) {
            int previous = current - 1;
            if (nums[current] == nums[previous]) {
                return previous;
            }
        }
        return -1;
    }
}
