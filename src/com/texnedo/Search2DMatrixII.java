package com.texnedo;

import java.util.Arrays;

public class Search2DMatrixII {
    public static void main(String[] args) {
        Search2DMatrixII matrixII = new Search2DMatrixII();
        int[][] data = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(matrixII.searchMatrix(data, 14));
        int [][] data1 = {
                {1,   4,  7, 11, 15},
                {18, 21, 23, 26, 30},
                {65, 89, 90, 93, 96}
        };
        System.out.println(matrixII.searchMatrixOrderedByRows(data1, 93));
    }

    public boolean searchMatrix(int[][] matrix,
                                int target) {
        if (matrix == null) {
            throw new IllegalArgumentException();
        }
        if (matrix.length == 0) {
            return false;
        }
        if (matrix[0].length == 0) {
            return false;
        }
        int[] searchStart = {0, 0};
        int[] searchEnd = {matrix.length - 1, matrix[matrix.length - 1].length - 1};
        /*
         * Here we can implement matrix division by square blocks and checking
         * that target lays inside the block. Doing like this we can achieve better performance
         * than the current implementation.
         */
        return searchMatrix(matrix, searchStart, searchEnd, target);
    }

    public boolean searchMatrix(int[][] matrix,
                                int[] searchStart,
                                int[] searchEnd,
                                int target) {
        printMatrix(matrix, searchStart, searchEnd);
        if (target > matrix[searchEnd[0]][searchEnd[1]]) {
            return false;
        }
        if (target < matrix[searchStart[0]][searchStart[1]]) {
            return false;
        }
        if (target == matrix[searchEnd[0]][searchEnd[1]] ||
                target == matrix[searchStart[0]][searchStart[1]]) {
            return true;
        }
        int middleI = searchStart[0] + (searchEnd[0] - searchStart[0]) / 2;
        if (middleI == searchStart[0] && middleI == searchEnd[0]) {
            if (Arrays.binarySearch(matrix[middleI], target) > 0) {
                return true;
            }
            return false;
        }
        int[] upperPartEnd = {middleI, searchEnd[1]};
        int[] lowerPartStart = {middleI + 1, searchStart[1]};
        if (searchMatrix(matrix, searchStart, upperPartEnd, target)) {
            return true;
        }
        if (searchMatrix(matrix, lowerPartStart, searchEnd, target)) {
            return true;
        }
        return false;
    }

    public void printMatrix(int[][] matrix,
                            int[] searchStart,
                            int[] searchEnd) {
        for (int i = searchStart[0]; i <= searchEnd[0]; i++) {
            for (int j = searchStart[1]; j <= searchEnd[1]; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean searchMatrixOrderedByRows(int[][] matrix, int target) {
        int startRow = 0;
        int endRow = matrix.length - 1;
        while (startRow <= endRow) {
            int middle = startRow + (endRow - startRow) / 2;
            int[] row = matrix[middle];
            if (row[0] == target) {
                return true;
            }
            if (row[row.length - 1] == target) {
                return true;
            }
            if (row[0] < target && target < row[row.length - 1]) {
                if (Arrays.binarySearch(row, target) > 0) {
                    return true;
                }
                return false;
            }
            if (row[0] > target) {
                endRow = middle - 1;
            } else {
                startRow = middle + 1;
            }
        }
        return false;
    }
}
