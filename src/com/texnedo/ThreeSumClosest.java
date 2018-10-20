package com.texnedo;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest sumClosest = new ThreeSumClosest();
        int[] nums = {-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33};
        System.out.println(sumClosest.threeSumClosest(nums, 0));
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int closestSum = nums[0];
        int closestDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            int sum = nums[i];
            int diff = Integer.MAX_VALUE;
            while (start < end) {
                int sumLocal = nums[i] + nums[start] + nums[end];
                System.out.println("sum=" + sumLocal);
                if (sumLocal == target) {
                    return target;
                }
                int diffLocal = Math.abs(target - sumLocal);
                if (Math.min(diffLocal, diff) == diffLocal) {
                    diff = diffLocal;
                    sum = sumLocal;
                }
                if (sumLocal > target) {
                    end--;
                } else {
                    start++;
                }
            }
            System.out.println(diff + "=>" + sum);
            if (Math.min(closestDiff, diff) == diff) {
                closestDiff = diff;
                closestSum = sum;
            }
        }
        return closestSum;
    }
}
