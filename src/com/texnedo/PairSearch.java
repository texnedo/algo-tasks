package com.texnedo;

import java.util.HashSet;
import java.util.List;

public class PairSearch {
    public static int pairs(int k, List<Integer> arr) {
        HashSet<Integer> hash = new HashSet<>(arr);
        HashSet<String> pairs = new HashSet<>();
        int count = 0;
        for (Integer value : arr) {
            int diff = value - k;
            if (hash.contains(diff)) {
                String key = String.format("%d_%d", Math.min(value, diff), Math.max(value, diff));
                if (!pairs.contains(key)) {
                    System.out.println(key);
                    count++;
                    pairs.add(key);
                }
            }
        }
        return count;
    }
}
