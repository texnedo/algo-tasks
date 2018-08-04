package com.texnedo;

import java.util.Arrays;
import java.util.Collections;

public class GenerateAllPermulations {
    public static void main(String[] args) {
        int[] data = new int[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }
        //
        printAll(data, 0);
        //
        System.out.println();
        int[] data1 = {5,1,1};
        printNext(data1);
    }

    public static void swap(int[] data, int left, int right) {
        int tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void printAll(int[] data, int start) {
        if (data == null) {
            return;
        }
        if (start >= data.length) {
            System.out.println(Arrays.toString(data));
            return;
        }
        for (int i = start; i < data.length; i++) {
            swap(data, start, i);
            printAll(data, start + 1);
            swap(data, start, i);
        }
    }

    public static void printNext(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        if (data.length > 1) {
            int pivotIndex = -1;
            for (int i = data.length - 2; i >= 0; i--) {
                if (data[i] < data[i + 1]) {
                    pivotIndex = i;
                    break;
                }
            }
            if (pivotIndex != -1) {
                int lastIndex = data.length - 1;
                for (int i = data.length - 1; i >= 0; i--) {
                    if (data[i] > data[pivotIndex]) {
                        lastIndex = i;
                        break;
                    }
                }
                swap(data, pivotIndex, lastIndex);
            }
            for (int i = pivotIndex + 1, j = data.length - 1; i < data.length && j >= 0; i++, j--) {
                if (i >= j) {
                    break;
                }
                swap(data, i, j);
            }
        }
        System.out.println(Arrays.toString(data));
    }
}
