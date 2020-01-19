package com.texnedo;

public class LargestSumSubarray {
    public static void main(String[] args) {
        LargestSumSubarray sum = new LargestSumSubarray();
        int[] data = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(sum.maxSumWithK(data));
    }

    public int maxSumWithK(int[] data) {
        int[] maxSum = new int[data.length];
        maxSum[0] = data[0];
        int totalMax = Integer.MIN_VALUE;
        for (int i = 1; i < data.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1] + data[i], data[i]);
        }
        for (int i = 0; i < data.length; i++) {
            if (totalMax < maxSum[i]) {
                totalMax = maxSum[i];
            }
        }
        return totalMax;
    }
}
