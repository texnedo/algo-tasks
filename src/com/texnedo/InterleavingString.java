package com.texnedo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class InterleavingString {
    public static void main(String[] args) {
        InterleavingString string = new InterleavingString();
        System.out.println(string.isInterleave("aa", "bb", "aabb"));
    }


    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isInterleaveInternal(s1, s2, s3, 0, 0, 0, new HashMap<>());
    }

    public boolean isInterleaveInternal(String s1, String s2, String s3,
                                        int index1, int index2, int index3,
                                        HashMap<String, Boolean> cache) {
        if (index3 == s3.length() && index1 == s1.length() && index2 == s2.length()) {
            return true;
        }
        if (index3 > s3.length()) {
            return false;
        }
        String key = String.format(Locale.US, "%d_%d_%d", index1, index2, index3);
        Boolean cached = cache.get(key);
        if (cached != null) {
            return cached;
        }
        char s3Current = s3.charAt(index3);
        boolean foundEqual = false;
        if (index1 < s1.length()) {
            char s1Current = s1.charAt(index1);
            System.out.println(s1Current);
            if (s1Current == s3Current) {
                foundEqual = isInterleaveInternal(s1, s2, s3, index1 + 1, index2, index3 + 1, cache);
            }
        }
        if (foundEqual) {
            return true;
        }
        if (index2 < s2.length()) {
            char s2Current = s2.charAt(index2);
            System.out.println(s2Current);
            if (s2Current == s3Current) {
                foundEqual = isInterleaveInternal(s1, s2, s3, index1, index2 + 1, index3 + 1, cache);
            }
        }
        cache.put(key, foundEqual);
        return foundEqual;
    }

    public boolean isInterleaveWrong(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        HashMap<Character, LinkedList<Integer>> s3CharMap = new HashMap<>();
        for (int i = 0; i < s3.length(); i++) {
            char current = s3.charAt(i);
            LinkedList<Integer> currentList = s3CharMap.get(current);
            if (currentList == null) {
                currentList = new LinkedList<>();
                s3CharMap.put(current, currentList);
            }
            currentList.add(i);
        }
        if (!checkInterleavingString(s1, s3CharMap)) {
            return false;
        }
        if (!checkInterleavingString(s2, s3CharMap)) {
            return false;
        }
        return s3CharMap.isEmpty();
    }

    private boolean checkInterleavingString(String s1, HashMap<Character, LinkedList<Integer>> s3CharMap) {
        int previousIndex = -1;
        for (int i = 0; i < s1.length(); i++) {
            char current = s1.charAt(i);
            LinkedList<Integer> currentList = s3CharMap.get(current);
            if (currentList == null) {
                return false;
            }
            if (currentList.isEmpty()) {
                return false;
            }
            int index = currentList.removeFirst();
            if (index > previousIndex) {
                previousIndex = index;
            } else {
                return false;
            }
            if (currentList.isEmpty()) {
                s3CharMap.remove(current);
            }
        }
        return true;
    }
}
