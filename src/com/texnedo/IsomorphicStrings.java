package com.texnedo;

import java.util.HashMap;

public class IsomorphicStrings {
    public static void main(String[] args) {
        IsomorphicStrings strings = new IsomorphicStrings();
        System.out.println(strings.isIsomorphic("agg", "sbb"));
        System.out.println(strings.isIsomorphic("agg", "sbx"));
        System.out.println(strings.isIsomorphic("avab", "acac"));
        System.out.println(strings.isIsomorphic("131", "423"));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        final HashMap<Character, Character> directMap = new HashMap<>();
        final HashMap<Character, Character> invertedMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final char directChar = s.charAt(i);
            final char invertedChar = t.charAt(i);
            if (directMap.get(directChar) == null && invertedMap.get(invertedChar) == null) {
                directMap.put(directChar, invertedChar);
                invertedMap.put(invertedChar, directChar);
            } else if (directMap.get(directChar) == null || invertedMap.get(invertedChar) == null) {
                return false;
            } else if (invertedChar != directMap.get(directChar)) {
                return false;
            }
        }
        return true;
    }
}
