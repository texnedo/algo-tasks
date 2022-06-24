package com.texnedo;

import java.util.*;

public class MaximumLengthConcatenatedStringUniqueCharacters {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("cha","r","act","ers");
        List<String> data1 = Arrays.asList("ab","cd","cde","cdef","efg","fgh","abxyz");
        List<String> data2 = Arrays.asList("un","iq","ue");
        System.out.println(maxLength(data2));
    }

    public static int maxLength(List<String> arr) {
        Collections.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -Integer.compare(o1.length(), o2.length());
            }
        });
        boolean[] used = new boolean[26];
        boolean[] usedIndexes = new boolean[arr.size()];
        boolean[][] localLetters = new boolean[arr.size()][];
        for (int i = 0; i < arr.size(); i++) {
            localLetters[i] = new boolean[26];
            for (int j = 0; j < arr.get(i).length(); j++) {
                char letter = arr.get(i).charAt(j);
                int offset = letter - 'a';
                if (localLetters[i][offset]) {
                    //exclude words with duplicates
                    usedIndexes[i] = true;
                    break;
                }
                localLetters[i][offset] = true;
            }
        }
        HashMap<Integer, Integer> cache = new HashMap<>();
        return maxLengthInternal(arr, localLetters, usedIndexes, used, cache);
    }

    private static int maxLengthInternal(List<String> arr,
                                         boolean[][] localLetters,
                                         boolean[] usedIndexes,
                                         boolean[] used,
                                         HashMap<Integer, Integer> cache) {
        int key = Arrays.hashCode(usedIndexes);
        Integer existing = cache.get(key);
        if (existing != null) {
            return existing;
        }
        for (int i = 0; i < usedIndexes.length; i++) {
            if (usedIndexes[i]) {
                System.out.print(arr.get(i) + " ");
            }
        }
        System.out.println();
        int maxLength = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (!usedIndexes[i]) {
                boolean hasUnique = true;
                for (int j = 0; j < localLetters[i].length; j++) {
                    if (localLetters[i][j] && used[j]) {
                        hasUnique = false;
                        break;
                    }
                }
                if (!hasUnique) {
                    continue;
                }
                for (int j = 0; j < localLetters[i].length; j++) {
                    if (localLetters[i][j]) {
                        used[j] = localLetters[i][j];
                    }
                }
                usedIndexes[i] = true;
                int length = arr.get(i).length() +
                        maxLengthInternal(arr, localLetters, usedIndexes, used, cache);
                if (maxLength < length) {
                    maxLength = length;
                }
                usedIndexes[i] = false;
                for (int j = 0; j < localLetters[i].length; j++) {
                    if (localLetters[i][j]) {
                        used[j] = false;
                    }
                }
            }
        }
        cache.put(key, maxLength);
        return maxLength;
    }
}
