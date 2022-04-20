package com.texnedo;

import java.util.*;

public class DivisibleSumPairs {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(divisibleSumPairs(data.size(), 5, data));
    }

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        ArrayList<Integer> allDivisible = new ArrayList<>();
        for (int i = 2; i <= 200; i++) {
            if (i % k == 0) {
                allDivisible.add(i);
            }
        }
        //TODO - add check self index
        HashSet<Integer> existing = new HashSet<>(ar);
        HashSet<String> pairs = new HashSet<>();
        int count = 0;
        for (Integer value : ar) {
            int greaterIndex = Collections.binarySearch(allDivisible, value);
            if (greaterIndex < 0) {
                greaterIndex = -greaterIndex - 1;
            }
            for (int i = greaterIndex; i < allDivisible.size(); i++) {
                int rest = allDivisible.get(i) - value;
                if (existing.contains(rest)) {
                    String pairKey = String.format("%d-%d",
                            Math.min(value, rest), Math.max(value, rest));
                    if (!pairs.contains(pairKey)) {
                        System.out.println(pairKey);
                        pairs.add(pairKey);
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
