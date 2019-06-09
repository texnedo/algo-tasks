package com.texnedo;

public class OnesAndZeroes {
    public static void main(String[] args) {
        OnesAndZeroes onesAndZeroes = new OnesAndZeroes();
        String[] data = {"10", "0001", "111001", "1", "0"};
        String[] data1 = {"10", "0", "1"};
        System.out.println(onesAndZeroes.findMaxForm(data, 5, 3));
        System.out.println(onesAndZeroes.findMaxForm(data1, 1, 1));
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] countsCache = new int[strs.length][];
        Integer[][][] computeCache = new Integer[strs.length][101][101];
        return findMaxFrom(strs, 0, m, n, computeCache, countsCache);
    }

    private static int findMaxFrom(String[] strs,
                                   int start,
                                   int m,
                                   int n,
                                   Integer[][][] computeCache,
                                   int[][] countsCache) {
        if (strs == null || computeCache == null) {
            throw new IllegalArgumentException();
        }
        if (start == strs.length) {
            return 0;
        }
        Integer cached = computeCache[start][m][n];
        if (cached != null) {
            return cached;
        }
        int[] counts = countOnesAndZeros(strs, start, countsCache);
        int countWithout = findMaxFrom(strs, start + 1, m, n, computeCache, countsCache);
        if (m < counts[0] || n < counts[1]) {
            computeCache[start][m][n] = countWithout;
            return countWithout;
        }
        int countWith = findMaxFrom(
                strs,
                start + 1,
                m - counts[0],
                n - counts[1],
                computeCache,
                countsCache) + 1;
        int minCount = Math.max(countWith, countWithout);
        computeCache[start][m][n] = minCount;
        return minCount;
    }

    private static int[] countOnesAndZeros(String[] strs, int index, int[][] countsCache) {
        if (strs == null || strs.length == 0 || strs.length <= index ||
                countsCache == null || countsCache.length != strs.length) {
            throw new IllegalArgumentException();
        }
        int[] count = countsCache[index];
        if (count == null) {
            count = countOnesAndZeros(strs[index]);
            countsCache[index] = count;
        }
        return count;
    }

    private static int[] countOnesAndZeros(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int[] count = new int[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                count[0]++;
            } else if (str.charAt(i) == '1') {
                count[1]++;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return count;
    }
}
