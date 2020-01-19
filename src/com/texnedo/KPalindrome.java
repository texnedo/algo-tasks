package com.texnedo;

public class KPalindrome {
    public static void main(String[] args) {
        KPalindrome palindrome = new KPalindrome();
        System.out.println(palindrome.isPalindrome("abcdecba", 1));
        System.out.println(palindrome.isPalindrome("abcdeca", 2));
        System.out.println(palindrome.isPalindrome("ab", 1));
        System.out.println(palindrome.isPalindrome("a", 1));
    }

    public boolean isPalindrome(String s, int k) {
        return isPalindromeInternal(s, k, 0, s.length() - 1);
    }

    public boolean isPalindromeInternal(String s, int k, int start, int end) {
        int i = start, j = end;
        while (s.length() % 2 == 0 ? i < j : i < j - 1) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                if (k == 0) {
                    return false;
                }
                if (isPalindromeInternal(s, k - 1, i + 1, j)) {
                    return true;
                }
                return isPalindromeInternal(s, k - 1, i, j - 1);
            }
        }
        return true;
    }
}
