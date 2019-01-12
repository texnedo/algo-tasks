package com.texnedo;

public class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutation palindrome = new PalindromePermutation();
        String s = "cdcdcdcdeeeef";
        System.out.println(palindrome.permutation(s));
    }

    private boolean permutation(String s) {
        final int[] counts = new int[27];
        for (int i = 0; i < s.length(); i++) {
            final int index = s.charAt(i) - 'a';
            counts[index]++;
        }
        int countOddCounts = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 != 0) {
                countOddCounts++;
                if (countOddCounts > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
