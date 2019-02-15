package com.texnedo;

public class FirstUniqueCharacterInString {
    public int firstUniqChar(String s) {
        char[] dict  = new char[27];
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int index = current - 'a';
            dict[index]++;
        }
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int index = current - 'a';
            if (dict[index] == 1) {
                return i;
            }
        }
        return -1;
    }
}
