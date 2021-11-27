package com.texnedo;

import java.util.*;

public class FindKClosestElements2 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> closest = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int d1 = Math.abs(x - o1);
                int d2 = Math.abs(x - o2);
                int dCompare = Integer.compare(d1, d2);
                if (dCompare != 0) {
                    return dCompare;
                }
                return Integer.compare(o1, o2);
            }
        });
        for (int i = 0; i < arr.length; i++) {
            closest.add(arr[i]);
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = k - 1; i >= 0; i--) {
            result.add(closest.poll());
        }
        Collections.sort(result);
        return result;
    }
}
