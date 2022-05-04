package com.texnedo;

import java.util.*;

public class MaximalSquare2 {
    public static void main(String[] arr) {
        int[][] data0 = {
                        {1, 1, 1},
                        {1, 1, 0},
                        {1, 0, 1}
                    };
        System.out.println(maximalSquare(data0));
        int[][] data3 = {
                {1, 1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0, 0}
        };
        System.out.println(maximalSquare(data3));
        int[][] data4 = {
                {1, 0},
                {0, 1}
        };
        System.out.println(maximalSquare(data4));
        int[][] data5 = {
                {1, 1, 1}
        };
        System.out.println(maximalSquare(data5));
        List<List<Integer>> data = Arrays.asList(
                Arrays.asList(1, 1, 1),
                Arrays.asList(1, 1, 0),
                Arrays.asList(1, 0, 1)
        );
    }

    public static int maximalSquare(int[][] matrix) {
        int maxSquare = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if (maxSquare < 1) {
                        maxSquare = 1;
                    }
                    int maxLength = 0;
                    for (int k = j; k < matrix[i].length; k++) {
                        if (matrix[i][k] != 1) {
                            break;
                        }
                        maxLength++;
                    }
                    for (int k = 1; k < maxLength; k++) {
                        int onesCount = 0;
                        for (int r = i; r <= i + k && r < matrix.length; r++) {
                            if (onesCount == -1) {
                                break;
                            }
                            for (int c = j; c <= j + k && c < matrix[r].length; c++) {
                                if (matrix[r][c] != 1) {
                                    onesCount = -1;
                                    break;
                                }
                                onesCount++;
                            }
                        }
                        int side = k + 1;
                        if (onesCount == side * side && side > maxSquare) {
                            maxSquare = side;
                        }
                    }
                }
            }
        }
        return maxSquare * maxSquare;
    }
}
