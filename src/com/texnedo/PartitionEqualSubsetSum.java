package com.texnedo;

import java.util.Arrays;
import java.util.HashSet;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] data = {1, 5, 11, 5};
        int[] data1 = {1, 2, 3, 5};
        int[] data2 = {28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79};
        PartitionEqualSubsetSum equalSubsetSum = new PartitionEqualSubsetSum();
        //System.out.println(equalSubsetSum.canPartition(data));
        //System.out.println(equalSubsetSum.canPartition(data1));
        System.out.println(equalSubsetSum.canPartition(data2));
    }

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        if (totalSum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] extracted = new boolean[200];
        HashSet<Integer> extractedCache = new HashSet<>();
        return canPartition(nums, extracted, totalSum, 0, extractedCache);
    }

    private boolean canPartition(int[] nums,
                                 boolean[] extracted,
                                 int totalSum,
                                 int extractedSum,
                                 HashSet<Integer> extractedCache) {
        if (totalSum == 2 * extractedSum) {
            return true;
        }
        int extractedHash = Arrays.hashCode(extracted);
        if (extractedCache.contains(extractedHash)) {
            return false;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (extracted[i]) {
                continue;
            }
            extracted[i] = true;
            boolean canPartition = canPartition(
                    nums,
                    extracted,
                    totalSum,
                    extractedSum + nums[i],
                    extractedCache
            );
            if (canPartition) {
                return true;
            }
            extractedCache.add(Arrays.hashCode(extracted));
            extracted[i] = false;
        }
        extractedCache.add(extractedHash);
        return false;
    }
}
