package com.texnedo;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 7, 4, 5, 6};
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }

    static void insertionSort(int[] arr) {
        print(arr);
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            print(arr);
        }
    }

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
