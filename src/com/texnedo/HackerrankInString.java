package com.texnedo;

public class HackerrankInString {
    public static String hackerrankInString(String s) {
        String sample = "hackerrank";
        if (s.length() < sample.length()) {
            return "NO";
        }
        int sampleIndex = 0;
        for (int i = 0; i < s.length() && sampleIndex < sample.length(); i++) {
            if (s.charAt(i) == sample.charAt(sampleIndex)) {
                sampleIndex++;
            }
        }
        if (sampleIndex == sample.length()) {
            return "YES";
        }
        return "NO";
    }
}
