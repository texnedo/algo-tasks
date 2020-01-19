package com.texnedo;

/**
 * https://www.geeksforgeeks.org/rearrange-a-string-in-sorted-order-followed-by-the-integer-sum/
 * */
public class RearrangeLetters {
    public static void main(String[] args) {
        RearrangeLetters letters = new RearrangeLetters();
        System.out.println(letters.rearrange("AC2BEW3"));
    }

    public String rearrange(String input) {
        int[] letterCounts = new int[26];
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (Character.isDigit(current)) {
                sum += Character.digit(current, 10);
            } else {
                int index = current - 'A';
                letterCounts[index]++;
            }
        }
        StringBuilder builder = new StringBuilder(input.length());
        for (int i = 0; i < letterCounts.length; i++) {
            for (int j = 0; j < letterCounts[i]; j++) {
                builder.append((char)('A' + i));
            }
        }
        builder.append(sum);
        return builder.toString();
    }
}
