package com.texnedo;

public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("ccc"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean [][] table = new boolean[s.length()][s.length()];
        int maxLength = 1;
        String maxPalindrome = s.substring(0, 1);
        for (int i = 0; i < table.length; i++) {
            table[i][i] = true;
            if (i < table.length - 1 && s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                if (maxLength == 1) {
                    maxLength = 2;
                    maxPalindrome = s.substring(i, i + 2);
                }
            }
        }
        for (int i = 3; i <= s.length(); i++) {
            for (int start = 0; start <= s.length() - i; start++) {
                int end = start + i - 1;
                if (table[start + 1][end - 1] && s.charAt(start) == s.charAt(end)) {
                    table[start][end] = true;
                    int length = end - start + 1;
                    if (length > maxLength) {
                        maxLength = length;
                        maxPalindrome = s.substring(start, end + 1);
                    }
                }
            }
        }
        return maxPalindrome;
    }
}
