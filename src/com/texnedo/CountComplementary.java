package com.texnedo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CountComplementary {
    public static void main(String[] args) {
        int [] arr = {Integer.MAX_VALUE, Integer.MAX_VALUE, 1};
        System.out.println(solution(Integer.MIN_VALUE, arr));
    }

    public static int solution(int K, int[] A) {
        Map<Long, Integer> complementary = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            long key = (long)A[i];
            Integer count = complementary.get(key);
            if (count == null) {
                complementary.put(key, 1);
            } else {
                complementary.put(key, count + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            long key =  (long) K - A[i];
            Integer indexesCount = complementary.get(key);
            if (indexesCount != null) {
                System.out.println(String.format("%d - %d (%d)", A[i], key, indexesCount));
                count += indexesCount;
            }
        }
        return count;
    }
}
