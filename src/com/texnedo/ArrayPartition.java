package com.texnedo;

import java.util.Arrays;

public class ArrayPartition {
    public int arrayPairSum(int[] nums) {
        int sum = 0;
        //1 2 3 4
        //0 1 2 3
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                sum += nums[i];
            }
        }
        return sum;
    }
}
