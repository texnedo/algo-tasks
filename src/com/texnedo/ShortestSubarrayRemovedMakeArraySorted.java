package com.texnedo;

public class ShortestSubarrayRemovedMakeArraySorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        int left = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[left]) {
                left++;
            } else {
                break;
            }
        }
        int right = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] <= arr[right]) {
                left--;
            } else {
                break;
            }
        }
        if (left > right) {
            return 0;
        }
        int result = Math.min(arr.length - left - 1, right);
        //TODO - check left border and then right border with binary search
        return 0;
    }
}
