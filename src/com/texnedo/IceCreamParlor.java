package com.texnedo;

import java.util.HashMap;

public class IceCreamParlor {
    static int[] icecreamParlor(int m, int[] arr) {
        HashMap<Integer, Integer> flavours = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            flavours.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int result = m - arr[i];
            Integer index = flavours.get(result);
            if (index != null && index != i) {
                return new int[]{Math.min(index + 1, i + 1), Math.max(index + 1, i + 1)};
            }
        }
        return arr;
    }
}
