package com.texnedo;

import java.util.*;

public class MinimumArrayUniqSum {
    public static void main(String[] args) {
        System.out.print(getMinimumUniqueSum(Arrays.asList(1, 1, 2, 2, 3, 3, 3, 8, 9)));
    }

    public static int getMinimumUniqueSum(List<Integer> arr) {
//        HashMap<Integer, Integer> counts = new HashMap<>();
//        for (Integer value : arr) {
//            Integer existing = counts.get(value);
//            if (existing == null) {
//                counts.put(value, 1);
//            } else {
//                counts.put(value, existing + 1);
//            }
//        }
//        int sum = 0;
//        for (Map.Entry<Integer, Integer> item : counts.entrySet()) {
//            if (item.getValue() == 1) {
//                sum += item.getKey();
//            } else {
//                sum += item.getKey() * (item.getValue() - 1);
//            }
//        }
        Collections.sort(arr);
        int sum = arr.get(0);
        int maxValue = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            int incremented;
            if (arr.get(i).equals(arr.get(i - 1))) {
                incremented = Math.max(arr.get(i), maxValue) + 1;
            } else {
                incremented = Math.max(arr.get(i), maxValue + 1);
            }
            if (incremented > maxValue) {
                maxValue = incremented;
            }
            sum += incremented;
        }
        return sum;
    }
}
