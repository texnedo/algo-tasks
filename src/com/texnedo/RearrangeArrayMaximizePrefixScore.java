package com.texnedo;

import jnr.ffi.annotations.In;

import java.util.Arrays;
import java.util.Comparator;

public class RearrangeArrayMaximizePrefixScore {
    public int maxScore(int[] nums) {
        Integer[] data = new Integer[nums.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = nums[i];
        }
        return maxScoreInternal(data);
    }
    public int maxScoreInternal(Integer[] nums) {
        Arrays.sort(nums, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        long sum = 0;
        int score = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > 0) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        int[] data = {2,-1,0,1,-3,3,-3};
        RearrangeArrayMaximizePrefixScore score = new RearrangeArrayMaximizePrefixScore();
        System.out.println(score.maxScore(data));
    }
}
