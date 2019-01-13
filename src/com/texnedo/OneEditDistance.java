package com.texnedo;

public class OneEditDistance {
    public static void main(String[] args) {
        OneEditDistance distance = new OneEditDistance();
        System.out.println(distance.hasOneEdit("abc", "qabc"));
    }

    public boolean hasOneEdit(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return false;
        }
        if (s1 == null || s1.length() == 0) {
            return s2.length() == 1;
        }
        if (s2 == null || s2.length() == 0) {
            return s1.length() == 1;
        }
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }
        int maxLength = Math.max(s1.length(), s2.length());
        Integer diffPosition = null;
        for (int i = 0; i < maxLength; i++) {
            if (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            diffPosition = i;
            break;
        }
        if (diffPosition == null) {
            return false;
        }
        //check keep as is
        boolean keepAsIs = true;
        for (int i = diffPosition + 1; i < maxLength; i++) {
            if (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            keepAsIs = false;
            break;
        }
        if (keepAsIs) {
            return true;
        }
        int minLength = Math.min(s1.length(), s2.length());
        //check remove from the first
        boolean successToRemoveFirst = true;
        for (int i = diffPosition; i < minLength; i++) {
            if (s1.charAt(i + 1) == s2.charAt(i)) {
                continue;
            }
            successToRemoveFirst = false;
            break;
        }
        if (successToRemoveFirst) {
            return true;
        }
        //check remove from the second
        boolean successToRemoveSecond = true;
        for (int i = diffPosition; i < minLength; i++) {
            if (s1.charAt(i) == s2.charAt(i + 1)) {
                continue;
            }
            successToRemoveSecond = false;
            break;
        }
        return successToRemoveSecond;
    }
}
