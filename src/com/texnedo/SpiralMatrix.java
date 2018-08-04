package com.texnedo;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix matrix = new SpiralMatrix();
        int[][] data = {{1, 2, 3}, { 4, 5, 6}, {7, 8, 9}};
        System.out.println(matrix.spiralOrder(data));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>(matrix.length);
        printRow(true, matrix, 0, result);
        printRow(false, matrix, 0, result);
        return result;
    }

    public void printRow(boolean left, int[][] matrix, int offset, List<Integer> result) {
        if (left) {
            for (int i = offset; i <= matrix[offset].length - 1 - offset; i++) {
                result.add(matrix[offset][i]);
            }
        } else {
            for (int i = matrix[offset].length - 1 - offset; i >= offset; i--) {
                result.add(matrix[matrix.length - 1 - offset][i]);
            }
        }
    }

    public void printColumn(boolean left, int[][] matrix, int offset, List<Integer> result) {
        if (left) {
            for (int i = offset; i <= matrix[offset].length - 1 - offset; i++) {
                result.add(matrix[offset][i]);
            }
        } else {
            for (int i = matrix[offset].length - 1 - offset; i >= offset; i--) {
                result.add(matrix[matrix.length - 1 - offset][i]);
            }
        }
    }
}
