package com.texnedo;


import java.util.ArrayList;
import java.util.List;

public class LeftmostColumnWithLeastOne {
    public static void main(String[] args) {
        LeftmostColumnWithLeastOne find = new LeftmostColumnWithLeastOne();
//        int[][] data = new int[][] {{0,0,0,1},{0,0,1,1},{0,1,1,1}};
//        System.out.println(find.leftMostColumnWithOne(new BinaryMatrix(data)));

//        int[][] data1 = new int[][] {{0,0},{0,1}};
//        System.out.println(find.leftMostColumnWithOne(new BinaryMatrix(data1)));

        int[][] data2 = new int[][] {{1,1,1,1,1},{0,0,0,1,1},{0,0,1,1,1},{0,0,0,0,1},{0,0,0,0,0}};
        System.out.println(find.leftMostColumnWithOne(new BinaryMatrix(data2)));
    }

    static class BinaryMatrix {
        private final int[][] data;
        private final List<Integer> size;

        List<Integer> dimensions() {
            return size;
        }

        public BinaryMatrix(int[][] input) {
            data = input;
            size = new ArrayList<>(2);
            size.add(data.length);
            size.add(data[0].length);
        }

        int get(int row, int col) {
            return data[row][col];
        }
    }

    public int leftMostColumnWithOneSlow(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (binaryMatrix.get(j, i) == 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int minCol = Integer.MAX_VALUE;
        for (int j = 0; j < rows; j++) {
            int currentCol = searchFirstOne(binaryMatrix, j, 0, cols - 1);
            if (currentCol == 0) {
                return 0;
            }
            if (currentCol == -1) {
                continue;
            }
            if (currentCol < minCol) {
                minCol = currentCol;
            }
        }
        return minCol == Integer.MAX_VALUE ? -1 : minCol;
    }

    private int searchFirstOne(BinaryMatrix binaryMatrix, int row, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (end - start) / 2 + start;
        int current = binaryMatrix.get(row, mid);
        if (current == 0) {
            return searchFirstOne(binaryMatrix, row, mid + 1, end);
        } else {
            if (mid == 0 || binaryMatrix.get(row, mid - 1) == 0) {
                return mid;
            } else {
                return searchFirstOne(binaryMatrix, row, start, mid - 1);
            }
        }
    }
}
