package com.texnedo;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        Integer[][] cache = new Integer[s.length()][s.length()];
        return longestPalindromeSubseqInternal(s, 0, s.length() - 1, cache);
    }

    private int longestPalindromeSubseqInternal(String s, int l, int r, Integer[][] cache) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return 1;
        }
        Integer existing = cache[l][r];
        if (existing != null) {
            return existing;
        }
        if (s.charAt(r) == s.charAt(l)) {
            int value = longestPalindromeSubseqInternal(s, l + 1, r - 1, cache) + 2;
            cache[l][r] = value;
            return value;
        }
        int left = longestPalindromeSubseqInternal(s, l, r - 1, cache);
        int right = longestPalindromeSubseqInternal(s, l + 1, r, cache);
        int value = Math.max(left, right);
        cache[l][r] = value;
        return value;
    }
}
