package com.texnedo;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int[] result = new int[mat.length * mat[0].length];
        boolean direction = true;
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < result.length) {
            while (i >= 0 && i < mat.length && j >= 0 && j < mat[0].length) {
                result[k] = mat[i][j];
                if (direction) {
                    i--;
                    j++;
                } else {
                    i++;
                    j--;
                }
                k++;
            }
            if (direction) {
                if (j >= mat[0].length) {
                    j = mat[0].length - 1;
                    i += 2;
                } else {
                    i = 0;
                }
            } else {
                if (i >= mat.length) {
                    i = mat.length - 1;
                    j += 2;
                } else {
                    j = 0;
                }
            }
            direction = !direction;
        }
        return result;
    }
}
