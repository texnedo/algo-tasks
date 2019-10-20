package com.texnedo;

import java.util.HashMap;

public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares squares = new PerfectSquares();
        System.out.println(squares.numSquares(12));
        System.out.println(squares.numSquares(13));
    }

    public int numSquares(int n) {
        final HashMap<Integer, Integer> dict = new HashMap<>();
        return numSquaresInternal(n, dict);
    }

    public int numSquaresInternal(int n, HashMap<Integer, Integer> dict) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        final Integer alreadyCounted = dict.get(n);
        if (alreadyCounted != null) {
            return alreadyCounted;
        }
        final int maxValue = (int) Math.sqrt(n);
        if (maxValue * maxValue == n) {
            return 1;
        }
        int minSquareCount = Integer.MAX_VALUE;
        for (int i = maxValue; i >= 1; i--) {
            final int squareCount = numSquaresInternal(n - i * i, dict);
            if (squareCount < minSquareCount) {
                minSquareCount = squareCount;
            }
        }
        minSquareCount = minSquareCount + 1;
        dict.put(n, minSquareCount);
        return minSquareCount;
    }
}
