package com.texnedo;

public class LongestPalindromeLength {
    public static void main(String[] args) {
        LongestPalindromeLength longestPalindrome = new LongestPalindromeLength();
        System.out.println(longestPalindrome.longestPalindrome("aa"));
    }

    public int longestPalindrome(String s) {
        final int[] counts = new int[30 * 2];
        for (int i = 0; i < s.length(); i++) {
            final int index = s.charAt(i) - 'A';
            counts[index]++;
        }
        int countOddCounts = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 != 0) {
                countOddCounts++;
            }
        }
        int toRemove = countOddCounts == 0 ? 0 : countOddCounts - 1;
        return s.length() - toRemove;
    }
}
