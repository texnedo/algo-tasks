package com.texnedo;

public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber number = new PalindromeNumber();
        System.out.println(number.isPalindrome(1221));
        System.out.println(number.isPalindrome(121));
        System.out.println(number.isPalindrome(12345));
        System.out.println(number.isPalindrome(123454321));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int length = 0;
        int value = x;
        while (value > 0) {
            value = value / 10;
            length++;
        }
        value = x;
        for (int i = 1, j = 0; i <= length / 2; i++, j++) {
            int right = value % 10;
            int high = (int) Math.pow(10, length - i - j);
            int left = value / high;
            if (left != right) {
                return false;
            }
            value = value % high;
            value = value / 10;
        }
        return true;
    }
}
