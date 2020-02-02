package com.texnedo;

import java.util.ArrayList;
import java.util.List;

public class MaximumLengthRepeatedSubarray {
    public static void main(String[] args) {
        MaximumLengthRepeatedSubarray array = new MaximumLengthRepeatedSubarray();
        int[] dataA = {1,2,3,2,1,4};
        int[] dataB = {3,2,1,4,7,1,2};
        System.out.println(array.findLength(dataA, dataB));
        System.out.println(array.findLength2(dataA, dataB));
    }

    public int findLength(int[] A, int[] B) {
        int[][] data = new int[A.length][B.length];
        int maxLength = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    System.out.println(A[i] + " -> " + B[j]);
                    if (i >= 1 && j >= 1) {
                        data[i][j] = data[i - 1][j - 1] + 1;
                    } else {
                        data[i][j] = 1;
                    }
                    if (maxLength < data[i][j]) {
                        maxLength = data[i][j];
                    }
                }
            }
        }
        return maxLength;
    }

    private int scanMaxEqual(int[] A, int[] B, int indexA, int indexB) {
        int i = indexA;
        int j = indexB;
        int count = 0;
        while (i < A.length && j < B.length) {
            if (A[i] != B[j]) {
                return count;
            }
            i++;
            j++;
            count++;
        }
        return count;
    }

    public int findLength2(int[] A, int[] B) {
        List<Integer>[] BDict = new List[100];
        for (int i = 0; i < B.length; i++) {
            List<Integer> current = BDict[B[i]];
            if (current == null) {
                current = new ArrayList<>();
                BDict[B[i]] = current;
            }
            current.add(i);
        }
        int maxLength = 0;
        //TODO - find if there is a start of a sequence in the second array
        int i = 0;
        while (i < A.length) {
            if (BDict[A[i]] != null) {
                int maxEqual = 0;
                for (int j : BDict[A[i]]) {
                    int currentEqual = scanMaxEqual(A, B, i, j);
                    if (maxEqual < currentEqual) {
                        maxEqual = currentEqual;
                    }
                }
                if (maxLength < maxEqual) {
                    maxLength = maxEqual;
                }
                i += maxEqual;
            } else {
                i++;
            }
        }
        return maxLength;
    }
}
