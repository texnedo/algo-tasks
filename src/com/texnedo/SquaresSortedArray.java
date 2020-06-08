package com.texnedo;

import java.util.Arrays;

public class SquaresSortedArray {
    /**
     * Other possible approach:
     * 1. use binary search to find first element less than 0
     * 2. merge two sorted arrays with standard algorithm
     * */
    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        boolean hasNegative = false;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                hasNegative = true;
            }
            result[i] = A[i] * A[i];
        }
        if (hasNegative) {
            Arrays.sort(result);
        }
        return result;
    }
}
