package com.texnedo;

/**
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
 * */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        SubarraySumEqualsK sumEqualsK = new SubarraySumEqualsK();
        int[] nums = {1, 1, 1};
        System.out.println(sumEqualsK.subarraySum(nums, 2));
        int[] nums1 = {1};
        System.out.println(sumEqualsK.subarraySum(nums1, 1));
        int[] nums2 = {1, 1, 1};
        System.out.println(sumEqualsK.subarraySum(nums2, 1));
        int[] nums3 = {1, 4, 20, 3, 10, 5};
        System.out.println(sumEqualsK.subarraySum(nums3, 33));
        int[] nums4 = {10, 2, -2, -20, 10};
        System.out.println(sumEqualsK.subarraySum(nums4, -10));

    }

    public int subarraySum(int[] nums, int k) {
        int sumStartIndex = 0;
        int sumTotal = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (sumTotal < k) {
                sumTotal += nums[i];
            }
            if (sumTotal == k) {
                count++;
                sumTotal -= nums[sumStartIndex];
                sumStartIndex++;
            } else {
                while (sumStartIndex < i && sumTotal > k) {
                    sumTotal -= nums[sumStartIndex];
                    sumStartIndex++;
                }
            }
        }
        if (sumTotal == k) {
            count++;
        }
        return count;
    }
}
