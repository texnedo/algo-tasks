package com.texnedo;

public class ValidPalindrome2 {
    public static void main(String[] args) {
        ValidPalindrome2 palindrome = new ValidPalindrome2();
        System.out.println(palindrome.validPalindrome("cbxgc"));
    }

    public boolean validPalindrome(String s) {
        return validPalindromeInternal(s, 0, s.length() - 1, 0);
    }

    public boolean validPalindromeInternal(String s, int left, int right, int errors) {
        if (errors > 1) {
            return false;
        }
        System.out.println(left + "-" + right);
        while(left <= right) {
            if (left == right) {
                return true;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return validPalindromeInternal(s, left + 1, right, errors + 1)
                        || validPalindromeInternal(s, left, right - 1, errors + 1);
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
