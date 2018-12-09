package com.texnedo;

import com.texnedo.utils.Matrix;

public class RotateMatrix {
    public static void main(String[] args) {
        RotateMatrix matrix = new RotateMatrix();
        int[][] data = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        Matrix.print(data);
        matrix.rotateCW(data);
        Matrix.print(data);
    }

    public void rotateCCW(int[][] matrix) {
        final int n = matrix.length - 1;
        for (int i = 0; i <= n / 2; i++) {
            for (int j = i; j < n - i; j++) {
                System.out.println(String.format("circle: %d %d %d %d",
                        matrix[i][j], matrix[j][n - i],
                        matrix[n - i][n - j], matrix[n - j][i])
                );
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][n - i];
                matrix[j][n - i] = matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[n - j][i];
                matrix[n - j][i] = tmp;
            }
        }
    }

    public void rotateCW(int[][] matrix) {
        final int n = matrix.length - 1;
        for (int i = 0; i <= n / 2; i++) {
            for (int j = i; j < n - i; j++) {
                System.out.println(String.format("circle: %d %d %d %d",
                        matrix[i][j], matrix[j][n - i],
                        matrix[n - i][n - j], matrix[n - j][i])
                );
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j][i];
                matrix[n - j][i] = matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n - i] = tmp;
            }
        }
    }
}
