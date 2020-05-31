package com.texnedo;

public class ReverseWordsStringIII {
    public static void main(String[] args) {
        ReverseWordsStringIII stringIII = new ReverseWordsStringIII();
        System.out.println(stringIII.reverseWords("Let's take LeetCode contest"));
    }

    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        int wordStart = -1;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == ' ') {
                if (wordStart != -1) {
                    for (int j = i - 1; j >= wordStart; j--) {
                        builder.append(s.charAt(j));
                    }
                    wordStart = -1;
                }
                builder.append(current);
            } else if (wordStart == -1) {
                wordStart = i;
            }
        }
        if (wordStart != -1) {
            for (int j = s.length() - 1; j >= wordStart; j--) {
                builder.append(s.charAt(j));
            }
        }
        return builder.toString();
    }
}
