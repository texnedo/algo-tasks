package com.texnedo;

public class ReverseString {
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            int last = s.length - 1 - i;
            s[i] = s[last];
            s[last] = tmp;
        }
    }
}
