package com.texnedo;

public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int capitalCount = 0;
        int notCapitalCount = 0;
        boolean hasFirstCapital = false;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (current >= 'a' && current <= 'z') {
                notCapitalCount++;
            }
            if (current >= 'A' && current <= 'Z') {
                capitalCount++;
                if (i == 0) {
                    hasFirstCapital = true;
                }
            }
        }
        if (hasFirstCapital && notCapitalCount == word.length() - 1) {
            return true;
        }
        if (notCapitalCount == word.length()) {
            return true;
        }
        if (capitalCount == word.length()) {
            return true;
        }
        return false;
    }
}
