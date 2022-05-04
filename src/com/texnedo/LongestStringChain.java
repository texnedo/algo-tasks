package com.texnedo;

import java.util.*;

public class LongestStringChain {
    public static void main(String[] args) {
        String[] data = {"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChain(data));
        String[] data1 = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(longestStrChain(data1));
        String[] data2 = {"a","ab","ac","bd","abc","abd","abdd"};
        System.out.println(longestStrChain(data2));
    }

    public static int longestStrChain(String[] words) {
        if (words.length == 0 || words.length == 1) {
            return 1;
        }
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        HashMap<String, List<Integer>> tokens = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= 2) {
                for (int j = 0; j < words[i].length(); j++) {
                    String token;
                    if (j < words[i].length() - 1) {
                        token = words[i].substring(0, j) + words[i].substring(j + 1);
                    } else {
                        token = words[i].substring(0, j);
                    }
                    List<Integer> list = tokens.get(token);
                    if (list == null) {
                        list = new LinkedList<>();
                        tokens.put(token, list);
                    }
                    list.add(i);
                }
            }
        }
        if (tokens.isEmpty()) {
            return 1;
        }
        HashSet<Integer> used = new HashSet<>();
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            if (used.contains(i)) {
                continue;
            }
            int length = 0;
            LinkedList<Integer> level = new LinkedList<>();
            level.add(i);
            while (!level.isEmpty()) {
                LinkedList<Integer> nextLevel = new LinkedList<>();
                for (Integer currentIndex : level) {
                    used.add(currentIndex);
                    List<Integer> nextIndexes = tokens.get(words[currentIndex]);
                    if (nextIndexes == null) {
                        continue;
                    }
                    for (Integer nextIndex : nextIndexes) {
                        if (!used.contains(nextIndex)) {
                            nextLevel.add(nextIndex);
                        }
                    }
                }
                level.clear();
                level.addAll(nextLevel);
                length++;
            }
            if (maxLength < length) {
                maxLength = length;
            }
        }
        return maxLength;
    }
}
