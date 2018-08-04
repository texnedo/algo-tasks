package com.texnedo;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] data = {0, 8, 4, 12, 2};
        System.out.println(lcs(data));
    }

    static int lcs(int[] data) {
        int[] dp = new int[data.length];
        int len = 0;
        for (int num : data) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
