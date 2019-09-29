package com.texnedo;

public class MaximumSubarray {
    /**
     * -1 -2 -3 -100 -5
     *
     * -2,1,-3,4,-1,2,1,-5,4
     * */
    public static void main(String[] args) {
        MaximumSubarray subarray = new MaximumSubarray();
        int[] data = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(subarray.maxSubArray(data));
    }

    public int maxSubArray(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }
}
