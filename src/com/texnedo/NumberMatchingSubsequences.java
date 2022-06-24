package com.texnedo;

import java.util.*;

public class NumberMatchingSubsequences {
    public static void main(String[] args) {
        //s = "abcde", words = ["a","bb","acd","ace"]
        //System.out.print(numMatchingSubseq("abcde", new String[] {"a","bb","acd","ace", "aex"}));
        System.out.print(numMatchingSubseq("dsahjpjauf", new String[] {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"}));
    }

    public static int numMatchingSubseq(String s, String[] words) {
        @SuppressWarnings("unchecked")
        List<Integer>[] search = new List[26];
        for (int i = 0; i < s.length(); i++) {
            int offset = s.charAt(i) - 'a';
            List<Integer> next = search[offset];
            if (next == null) {
                next = new ArrayList<>();
                search[offset] = next;
            }
            next.add(i);
        }
        for (int i = 0; i < search.length; i++) {
            if (search[i] != null) {
                Collections.sort(search[i]);
            }
        }
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            int maxFromMin = 0;
            List<Integer> previous = null;
            int letterIndex = 0;
            for (; letterIndex < words[i].length(); letterIndex++) {
                char c = words[i].charAt(letterIndex);
                int offset = c - 'a';
                List<Integer> next = search[offset];
                if (next == null) {
                    break;
                }
                if (previous != null) {
                    int minPrevious = previous.get(0);
                    int result = Collections.binarySearch(next, minPrevious);
                    if (result >= 0) {
                        break;
                    }
                    result = -result -1;
                    if (result == next.size()) {
                        //no index greater than current
                        break;
                    }
                    List<Integer> nextPossible = new ArrayList<>();
                    for (int k = result; k < next.size(); k++) {
                        int index = next.get(k);
                        if (index > maxFromMin) {
                            nextPossible.add(index);
                        }
                    }
                    if (nextPossible.isEmpty()) {
                        break;
                    }
                    previous = nextPossible;
                    if (maxFromMin < previous.get(0)) {
                        maxFromMin = previous.get(0);
                    }
                } else {
                    previous = next;
                    maxFromMin = next.get(0);
                }
            }
            if (letterIndex == words[i].length()) {
                count++;
            }
        }
        return count;
    }
}
