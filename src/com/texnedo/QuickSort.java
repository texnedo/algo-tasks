package com.texnedo;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = {3, 2, 1, 7, 4, 5, 6, 0};
        quickSort(data);
        System.out.println(Arrays.toString(data));
    }

    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    static private void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        print(arr);
        int position = partition(arr, start, end);
        quickSort(arr, start, position - 1);
        quickSort(arr, position + 1, end);
    }

    private static void swap(int[] data, int left, int right) {
        int tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    static private int partition(int[] arr, int start, int end) {
        if (start == end) {
            return start;
        }
        int partitionKey = arr[start];
        int firstBiggerIndex = -1;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] > partitionKey) {
                if (firstBiggerIndex == -1) {
                    firstBiggerIndex = i;
                }
            } else {
                if (firstBiggerIndex != -1) {
                    swap(arr, i, firstBiggerIndex);
                    firstBiggerIndex = firstBiggerIndex + 1;
                }
            }
        }
        if (firstBiggerIndex == -1) {
            swap(arr, start, end);
            return end;
        }
        swap(arr, start, firstBiggerIndex - 1);
        return firstBiggerIndex - 1;
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

    static void print(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
