package com.texnedo;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (t.length() < s.length()) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        int sIndex = 0;
        for (int tIndex = 0; tIndex < t.length() && sIndex < s.length(); tIndex++) {
            char sItem = s.charAt(sIndex);
            char tItem = t.charAt(tIndex);
            if (sItem == tItem) {
                sIndex++;
            }
            if (sIndex == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsSubsequence subsequence = new IsSubsequence();
        System.out.println(subsequence.isSubsequence("abc", "ahbgdc"));
    }
}
