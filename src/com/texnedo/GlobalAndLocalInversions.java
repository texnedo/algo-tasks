package com.texnedo;

public class GlobalAndLocalInversions {
    /**
     * 1 2 3 4 5 6 7 8
     * 1 3 2 4 5 7 6 8
     *
     * 1 3 2 8 5 7 6 4
     *
     * 1,0,2
     * 0,1,2
     *
     * 1,2,0
     *
     * */
    public boolean isIdealPermutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int diff = Math.abs(A[i] - i);
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }
}
