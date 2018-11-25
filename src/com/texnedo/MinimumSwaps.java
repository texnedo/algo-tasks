package com.texnedo;

public class MinimumSwaps {
    public static void main(String[] args) {
        MinimumSwaps swaps = new MinimumSwaps();
        int[] data = {7, 1, 3, 2, 4, 5, 6};
        System.out.println(swaps.minimumSwaps(data));
    }

    int minimumSwaps(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            swaps += traverseSwaps(arr, i);
        }
        return swaps;
    }

    int traverseSwaps(int[] arr, int i) {
        if (arr[i] == i + 1) {
            return 0;
        }
        int dest = arr[i] - 1;
        swap(arr, i, dest);
        return traverseSwaps(arr, i) + 1;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
