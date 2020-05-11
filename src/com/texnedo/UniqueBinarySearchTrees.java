package com.texnedo;

import java.util.HashMap;

public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        UniqueBinarySearchTrees trees = new UniqueBinarySearchTrees();
        System.out.println(trees.numTrees(3));
    }

    public int numTrees(int n, int[] cache) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (cache[n - 1] != 0) {
            return cache[n - 1];
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += numTrees(i) * numTrees(n - i - 1);
        }
        cache[n - 1] = n;
        return total;
    }

    public int numTrees(int n) {
        int[] cache = new int[n];
        return numTrees(n, cache);
    }
}
