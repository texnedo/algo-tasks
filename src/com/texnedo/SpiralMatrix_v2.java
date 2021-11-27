package com.texnedo;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix_v2 {
    public static void main(String[] args) {
        int[][] data = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] data1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        SpiralMatrix_v2 mat = new SpiralMatrix_v2();
        //System.out.println(mat.spiralOrder(data));
        System.out.println(mat.spiralOrder(data1));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> result = new LinkedList<>();
        spiralOrderInternal(
                matrix,
                result,
                new int[] {0, 0},
                new int[] {matrix.length - 1, matrix[0].length - 1}
                );
        return result;
    }

    private void spiralOrderInternal(int[][] matrix, List<Integer> result,
                                     int[] leftup, int[] rightdown) {
        if (leftup[0] > rightdown[0] || leftup[1] > rightdown[1]) {
            return;
        }
        for (int j = leftup[1]; j <= rightdown[1]; j++) {
            result.add(matrix[leftup[0]][j]);
        }
        for (int i = leftup[0] + 1; i <= rightdown[0] - 1; i++) {
            result.add(matrix[i][rightdown[1]]);
        }
        for (int j = rightdown[1]; j >= leftup[1] && leftup[0] != rightdown[0]; j--) {
            result.add(matrix[rightdown[0]][j]);
        }
        for (int i = rightdown[0] - 1; i >= leftup[0] + 1 && leftup[1] != rightdown[1]; i--) {
            result.add(matrix[i][leftup[1]]);
        }
        spiralOrderInternal(
                matrix,
                result,
                new int[] {leftup[0] + 1, leftup[1] + 1},
                new int[] {rightdown[0] - 1, rightdown[1] - 1}
        );
    }
}
