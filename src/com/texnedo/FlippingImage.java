package com.texnedo;

public class FlippingImage {
    public static void main(String[] args) {
        int[][] data = {{1,1,0},{1,0,1},{0,0,0}};
        FlippingImage image = new FlippingImage();
        image.flipAndInvertImage(data);
    }

    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            flipAndInvertRow(A[i]);
        }
        return A;
    }

    private void flipAndInvertRow(int[] row) {
        for (int i = 0; i < row.length / 2; i++) {
            int mirrorIndex = row.length - 1 - i;
            int tmp = row[mirrorIndex];
            row[mirrorIndex] = row[i] == 1 ? 0 : 1;
            row[i] = tmp == 1 ? 0 : 1;
        }
        if (row.length % 2 != 0) {
            int middle = row.length / 2;
            row[middle] = row[middle] == 1 ? 0 : 1;
        }
    }
}
