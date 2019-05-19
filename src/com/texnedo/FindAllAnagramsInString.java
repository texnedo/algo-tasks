package com.texnedo;

import java.util.*;

public class FindAllAnagramsInString {
    public static void main(String[] args) {
        FindAllAnagramsInString all = new FindAllAnagramsInString();
        System.out.println(all.findAnagrams("cbac", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null ||
                s.length() == 0 || p.length() == 0 ||
                s.length() < p.length()) {
            return Collections.emptyList();
        }
        int[] dict = new int[27];
        int[] found = new int[27];
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            int index = ch - 'a';
            dict[index]++;
        }
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + p.length() > s.length()) {
                break;
            }
            Arrays.fill(found, 0);
            boolean allFound = true;
            for (int j = 0; j < p.length(); j++) {
                char ch = s.charAt(i + j);
                int index = ch - 'a';
                int available = dict[index];
                if (available != 0 && found[index] < available) {
                    found[index]++;
                } else {
                    allFound = false;
                    break;
                }
            }
            if (allFound) {
                result.add(i);
            }
        }
        return result;
    }
}
