package com.texnedo;

import java.util.Arrays;

public class SmallestRangeII {
    public static void main(String[] args) {

    }

    public int smallestRangeII(int[] A, int K) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException();
        }
        if (A.length == 1) {
            return 0;
        }
        Arrays.sort(A);
        int maxA = A[A.length - 1];
        int minA = A[0];
        int diff = maxA - minA;
        for (int i = 0; i < A.length - 1; i++) {
            int high = Math.max(maxA - K, A[i] + K);
            int low = Math.min(minA + K, A[i + 1] - K);
            diff = Math.min(diff, high - low);
        }
        return diff;
    }
}
