package com.texnedo;

import java.util.Arrays;

public class SortArrayByParity {
    public static void main(String[] args) {
        SortArrayByParity sort = new SortArrayByParity();
        int[] data = {3,1,2,4};
        System.out.println(Arrays.toString(sort.sortArrayByParity(data)));
    }

    public int[] sortArrayByParity(int[] A) {
        int[] result = new int[A.length];
        int evenLastPos = 0;
        int oddLastPos = A.length - 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                result[evenLastPos] = A[i];
                evenLastPos++;
            } else {
                result[oddLastPos] = A[i];
                oddLastPos--;
            }
        }
        return result;
    }
}
