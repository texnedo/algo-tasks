package com.texnedo;

public class SherlockAndValidString {
    public static void main(String[] args) {
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println(isValid("aaaaabc"));
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabcd"));
        System.out.println(isValid("aa"));
        System.out.println(isValid("a"));
        System.out.println(isValid("abbcccdddd"));
    }

    public static String isValid(String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            dict[index]++;
        }
        int minCountValue = Integer.MAX_VALUE;
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                if (minCountValue > dict[i]) {
                    minCountValue = dict[i];
                }
            }
        }
        int errorCount = 0;
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0 && minCountValue != dict[i]) {
                if (dict[i] != minCountValue + 1) {
                    return "NO";
                }
                if (errorCount == 1) {
                    return "NO";
                }
                errorCount++;
            }
        }
        return "YES";
    }
}
