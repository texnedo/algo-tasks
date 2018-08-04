package com.texnedo;

public class StringPalindrome {
    public static void main(String[] args) {
        StringPalindrome palindrome = new StringPalindrome();
        System.out.println(palindrome.isPalindrome("  "));
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            boolean leftCorrect = Character.isAlphabetic(left) || Character.isDigit(left);
            boolean rightCorrect = Character.isAlphabetic(right) || Character.isDigit(right);
            if (leftCorrect && rightCorrect) {
                if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                    return false;
                }
                i++;
                j--;
            } else if (leftCorrect) {
                j--;
            } else if (rightCorrect) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}
