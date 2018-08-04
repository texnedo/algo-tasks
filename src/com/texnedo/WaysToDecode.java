package com.texnedo;

public class WaysToDecode {
    public static void main(String[] args) {
        WaysToDecode decode = new WaysToDecode();
        System.out.println(decode.numDecodings("123"));
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] computed = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            computed[i] = -1;
        }
        return numDecodingsInternal(s, 0, computed);
    }

    public int numDecodingsInternal(String s, int start, int[] computed) {
        if (start >= s.length()) {
            return 1;
        }
        if (s.charAt(start) == '0') {
            return 0;
        }
        if (computed[start] != -1) {
            return computed[start];
        }
        int countWays = numDecodingsInternal(s, start + 1, computed);
        if (start < s.length() - 1) {
            String token = s.substring(start, start + 2);
            int value = Integer.parseInt(token);
            if (value <= 26) {
                countWays += numDecodingsInternal(s, start + 2, computed);
            }
        }
        computed[start] = countWays;
        return countWays;
    }
}
