package com.texnedo;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        MaximumProductSubarray subarray = new MaximumProductSubarray();
        int[] data = {2,-1,1,1};
        System.out.println(subarray.maxProduct2(data));
        int[] data1 = {-2,-3,7};
        System.out.println(subarray.maxProduct2(data1));
    }

    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                int currentProduct = product * nums[j];
                if (currentProduct > maxProduct) {
                    maxProduct = currentProduct;
                }
                product = currentProduct;
            }
        }
        return maxProduct;
    }

    public int maxProduct2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int maxProduct = nums[0];
        int minPrevious = nums[0];
        int maxPrevious = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currentMax = maxPrevious * nums[i];
            int currentMin = minPrevious * nums[i];
            maxPrevious = Math.max(currentMax, Math.max(nums[i], currentMin));
            minPrevious = Math.min(currentMax, Math.min(nums[i], currentMin));
            if (maxPrevious > maxProduct) {
                maxProduct = maxPrevious;
            }
        }
        return maxProduct;
    }
}
