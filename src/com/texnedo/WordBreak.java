package com.texnedo;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        String[] dict  = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList(dict)));
    }

    public static boolean wordBreakInternal(String s, Set<String> dict, Map<String, Boolean> checked) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (dict.contains(s)) {
            return true;
        }
        Boolean existing = checked.get(s);
        if (existing != null && !existing) {
            return false;
        }
        for (int i = 1; i <= s.length(); i++) {
            String token = s.substring(0, i);
            if (dict.contains(token)) {
                if (wordBreakInternal(s.substring(i), dict, checked)) {
                    return true;
                }
            }
        }
        checked.put(s, false);
        return false;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> checked = new HashMap<>();
        return wordBreakInternal(s, dict, checked);
    }
}
