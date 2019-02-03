package com.texnedo;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();
        String[] words = {"hot", "dog"};
        System.out.println(
                ladder.ladderLength2("hot","dog", Arrays.asList(words))
        );
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> visited = new HashSet<>();
        HashMap<String, Integer> minLevels = new HashMap<>();
        return ladderLengthInternal(beginWord, endWord, wordList, visited, minLevels, 1);
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        return ladderLengthInternalBFS(beginWord, endWord, wordSet);
    }

    private int ladderLengthInternalBFS(String beginWord,
                                        String endWord,
                                        Set<String> wordSet) {
        Queue<String> current = new LinkedList<>();
        Queue<String> next = new LinkedList<>();
        current.offer(beginWord);
        int length = 0;
        while (!current.isEmpty()) {
            System.out.println(current);
            length++;
            while (!current.isEmpty()) {
                String node = current.poll();
                if (node == null) {
                    return 0;
                }
                if (node.equals(endWord)) {
                    return length;
                }
                for (String adjacent : wordSet) {
                    if (isOneEdit(node, adjacent)) {
                        next.offer(adjacent);
                    }
                }
                for (String added : next) {
                    wordSet.remove(added);
                }
            }
            Queue<String> tmp = current;
            current = next;
            next = tmp;
        }
        return 0;
    }

    private int ladderLengthInternal(String beginWord,
                                    String endWord,
                                    List<String> wordList,
                                    Set<String> visited,
                                    HashMap<String, Integer> minLevels,
                                    int level) {
        if (beginWord.equals(endWord)) {
            return level;
        }
        Integer computedLevelDiff = minLevels.get(beginWord);
        if (computedLevelDiff != null) {
            if (computedLevelDiff == 0) {
                return 0;
            }
            return level + computedLevelDiff;
        }
        int minLength = Integer.MAX_VALUE;
        for (String item : wordList) {
            if (isOneEdit(beginWord, item) && !visited.contains(item)) {
                visited.add(item);
                int length = ladderLengthInternal(
                        item, endWord, wordList,
                        visited, minLevels, level + 1
                );
                visited.remove(item);
                if (length < minLength && length > 0) {
                    minLength = length;
                }
            }
        }
        int result = minLength == Integer.MAX_VALUE ? 0 : minLength;
        minLevels.put(beginWord, result == 0 ? 0 : result - level);
        return result;
    }

    private boolean isOneEdit(String src, String dst) {
        if (src == null || dst == null || src.length() != dst.length()) {
            throw new IllegalArgumentException();
        }
        int errorCount = 0;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) != dst.charAt(i)) {
                errorCount++;
            }
            if (errorCount > 1) {
                return false;
            }
        }
        return true;
    }
}
