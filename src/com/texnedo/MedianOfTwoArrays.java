package com.texnedo;

public class MedianOfTwoArrays {
    public int findMedian(int[] x, int[]y) {
        return -1;
    }

    private boolean checkIfSplitOk(int[] x, int pivotX, int[] y, int pivotY) {
        return x[pivotX] < y[pivotY + 1] && x[pivotX + 1] < y[pivotY];
    }
}
