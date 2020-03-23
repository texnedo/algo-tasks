package com.texnedo;

public class FindSmallestDivisorGivenThreshold {
    public static void main(String[] args) {
        FindSmallestDivisorGivenThreshold threshold = new FindSmallestDivisorGivenThreshold();
        int[] data1 = {1,2,5,9};
        System.out.println(threshold.smallestDivisor(data1, 6));
        int[] data2 = {2,3,5,7,11};
        System.out.println(threshold.smallestDivisor(data2, 11));
        int[] data3 = {19};
        System.out.println(threshold.smallestDivisor(data3, 5));
        int[] data4 = {91,41,78,86,8};
        System.out.println(threshold.smallestDivisor(data4, 114));
    }

    public int smallestDivisor(int[] nums, int threshold) {
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        double minValue = Math.floor(sum / threshold);
        double maxValue = Math.ceil(sum / (threshold - nums.length));
        double minDivisor = Integer.MAX_VALUE;
        while (true) {
            double middle = (int) ((maxValue - minValue) / 2 + minValue);
            double currentSum = 0;
            for (int i = 0; i < nums.length; i++) {
                currentSum += Math.ceil(nums[i] / middle);
            }
            if (currentSum == threshold) {
                return (int) middle;
            }
            if (currentSum > threshold) {
                if (minValue == middle) {
                    minValue = maxValue;
                } else {
                    minValue = middle;
                }
            } else {
                maxValue = middle;
                if (middle < minDivisor) {
                    minDivisor = middle;
                }
            }
            if (currentSum >= sum) {
                break;
            }
            sum = currentSum;
        }
        if (minDivisor == Integer.MAX_VALUE) {
            return 1;
        }
        return (int) minDivisor;
    }
}
