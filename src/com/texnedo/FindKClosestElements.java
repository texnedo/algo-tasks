package com.texnedo;

import java.util.*;

public class FindKClosestElements {

    public static void main(String[] arr) {
        FindKClosestElements elements = new FindKClosestElements();
        int[] data = {1,2,3,4,5};
        System.out.println(elements.findClosestElements(data, 4, 3));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);
        boolean exclude = true;
        if (index < 0) {
            index = -index - 1;
            exclude = false;
        }
        if (index == arr.length) {
            index--;
        }
        int start = index - k;
        ArrayList<Integer> result = new ArrayList<>(k);
        for (int i = exclude ? start : start + 1;
             (exclude ? (i < index) : (i <= index)) && start >= 0;
             i++) {
            result.add(arr[i]);
        }
        while (result.size() < k) {
            result.add(arr[exclude ? ++index : index++]);
        }
        return result;
    }
}
