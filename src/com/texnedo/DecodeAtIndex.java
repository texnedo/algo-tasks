package com.texnedo;

public class DecodeAtIndex {
    public String decodeAtIndex(String S, int K) {
        int maxSize = 0;
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            if (Character.isDigit(current)) {
                maxSize *= Character.digit(current, 10);
            } else {
                maxSize++;
            }
        }
        for (int i = S.length() - 1; i >= 0; i--) {
            char current = S.charAt(i);
            K %= maxSize;
            if (K == 0 && !Character.isDigit(current)) {
                return "" + current;
            }
            if (Character.isDigit(current)) {
                maxSize /= Character.digit(current, 10);
            } else {
                maxSize--;
            }
        }
        throw new IllegalArgumentException();
    }
}
