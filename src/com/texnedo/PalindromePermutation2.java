package com.texnedo;

public class PalindromePermutation2 {
    public boolean canPermutePalindrome(String s) {
        int[] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int offset = s.charAt(i) - 'a';
            alpha[offset]++;
        }
        if (s.length() % 2 == 0) {
            for (int i = 0; i < alpha.length; i++) {
                if (alpha[i] != 0 && alpha[i] % 2 != 0) {
                    return false;
                }
            }
        } else {
            int countOdd = 0;
            for (int i = 0; i < alpha.length; i++) {
                if (alpha[i] != 0 && alpha[i] % 2 != 0) {
                    if (countOdd > 1) {
                        return false;
                    }
                    countOdd++;
                }
            }
        }
        return true;
    }
}
