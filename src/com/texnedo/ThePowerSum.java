package com.texnedo;

import java.util.HashSet;

public class ThePowerSum {
    public static void main(String[] args) {
        System.out.println(powerSum(100, 2));
    }

    public static int powerSum(int X, int N) {
        return powerSumInternal(X, N, new HashSet<>());
    }

    public static int powerSumInternal(int X, int N, HashSet<Integer> used) {
        if (X <= 0) {
            return 0;
        }
        int maxNum = (int) Math.pow(100, 1.0 / N);
        int totalCount = 0;
        for (int i = 1; i <= maxNum; i++) {
            if (used.contains(i)) {
                continue;
            }
            int powered = (int) Math.pow(i, N);
            if (X == powered) {
                used.add(i);
                totalCount += 1;
            } else {
                used.add(i);
                totalCount += powerSumInternal(X - powered, N, used);
                used.remove(i);
            }
        }
        return totalCount;
    }
}
