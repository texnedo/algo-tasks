package com.texnedo;

public class MaxSumSubarray {
    public static void main(String[] args) {
        MaxSumSubarray sumSubarray = new MaxSumSubarray();
        //int[] data = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] data = {-2, 1};
        System.out.println(sumSubarray.maxSubArray(data));
    }

    /**
     * Kadane's algorithm: see video https://www.youtube.com/watch?v=86CQq3pKSUw
     * */
    public int maxSubArray(int[] data) {
        int maxSum = Integer.MIN_VALUE;
        int previousSum = 0;
        for (int i = 0; i < data.length; i++) {
            int current = previousSum + data[i];
            if (current >= data[i]) {
                previousSum = current;
            } else {
                previousSum = data[i];
                current = data[i];
            }
            if (maxSum < current) {
                maxSum = current;
            }
        }
        return maxSum;
    }
}
