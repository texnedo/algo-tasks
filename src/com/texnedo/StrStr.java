package com.texnedo;

public class StrStr {
    public static void main(String[] args) {
        StrStr str = new StrStr();
        System.out.println(str.strStr("mississippi", "issipi"));
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.length() == 0) {
            return needle.length() == 0 ? 0 : -1;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            boolean matched = true;
            for (int j = 0; j < needle.length(); j++) {
                int offset = i + j;
                if (offset >= haystack.length()) {
                    return -1;
                }
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return i;
            }
        }
        return -1;
    }
}
