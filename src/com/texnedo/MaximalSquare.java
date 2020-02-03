package com.texnedo;

import java.util.Arrays;

public class MaximalSquare {
    public static void main(String[] args) {
        int[][] data =
                {
                        {1, 0, 1, 0, 0},
                        {1, 0, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 1, 0}

                };
        int[][] data1 = {
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 1, 1, 1}

        };
        MaximalSquare square = new MaximalSquare();
        System.out.println(square.maximalSquare(data));
        System.out.println(square.maximalSquare(data1));
    }

    public int maximalSquare(int[][] matrix) {
        int maxSquareSize = 0;
        int[] accumulate = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    accumulate[j]++;
                } else {
                    accumulate[j] = 0;
                }
            }
            int currentSquareSize = processAccumulate(accumulate);
            if (currentSquareSize > maxSquareSize) {
                maxSquareSize = currentSquareSize;
            }
        }
        return maxSquareSize;
    }

    public int processAccumulate(int[] accumulate) {
        int sameNumberCount = 0;
        Integer previousNumber = null;
        int maxSquareSize = 0;
        for (int i = 0; i < accumulate.length; i++) {
            if (accumulate[i] != 0) {
                if (previousNumber == null) {
                    sameNumberCount = 1;
                } else if (accumulate[i] == previousNumber) {
                    sameNumberCount++;
                } else {
                    if (previousNumber == sameNumberCount) {
                        //we have square value
                        if (maxSquareSize < sameNumberCount) {
                            maxSquareSize = sameNumberCount;
                        }
                    }
                    sameNumberCount = 1;
                }
                previousNumber = accumulate[i];
            } else {
                if (previousNumber != null && previousNumber == sameNumberCount) {
                    //we have square value
                    if (maxSquareSize < sameNumberCount) {
                        maxSquareSize = sameNumberCount;
                    }
                }
                sameNumberCount = 0;
                previousNumber = null;
            }
        }
        if (previousNumber != null && previousNumber == sameNumberCount) {
            //we have square value
            if (maxSquareSize < sameNumberCount) {
                maxSquareSize = sameNumberCount;
            }
        }
        System.out.println(Arrays.toString(accumulate));
        System.out.println("Max square = " + maxSquareSize);
        return maxSquareSize;
    }
}
