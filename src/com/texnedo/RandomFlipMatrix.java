package com.texnedo;

import java.util.Arrays;
import java.util.Random;

public class RandomFlipMatrix {
    private final int[] data;
    private int oneCount = 0;
    private final int rowsCount;
    private final int colsCount;
    private final Random rnd = new Random();

    public static void main(String[] args) {
        RandomFlipMatrix matrix = new RandomFlipMatrix(3, 4);
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println();
        matrix.reset();
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
        System.out.println(Arrays.toString(matrix.flip()));
    }

    public RandomFlipMatrix(int n_rows, int n_cols) {
        rowsCount = n_rows;
        colsCount = n_cols;
        data = new int[n_rows * n_cols];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
    }

    public int[] flip() {
        final int maxIndex = data.length - oneCount;
        final int randomIndex = rnd.nextInt(maxIndex);
        //build result
        final int[] result = new int[2];
        result[0] = data[randomIndex] / colsCount;
        result[1] = data[randomIndex] % colsCount;
        //move selected value to the end
        int tmp = data[maxIndex - 1];
        data[maxIndex - 1] = data[randomIndex];
        data[randomIndex] = tmp;
        oneCount++;
        return result;
    }

    public void reset() {
        oneCount = 0;
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
    }
}
