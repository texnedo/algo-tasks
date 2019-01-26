package com.texnedo;

import java.util.*;

public class GroupAnagrams {
    private static int[] dict = new int[27];

    public static void main(String[] args) {
        GroupAnagrams anagrams = new GroupAnagrams();
        String[] data = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(anagrams.groupAnagrams(data));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        if (strs.length == 1) {
            return Collections.singletonList(Collections.singletonList(strs[0]));
        }
        HashMap<String, List<String>> result = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String sorted = getSorted(strs[i]);
            List<String> current = result.get(sorted);
            if (current == null) {
                current = new LinkedList<>();
                result.put(sorted, current);
            }
            current.add(strs[i]);
        }
        return new ArrayList<>(result.values());
    }

    private String getSorted(String str) {
        Arrays.fill(dict, 0);
        int indexPrevious = -1;
        boolean isSorted = true;
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            int index = current - 'a';
            dict[index]++;
            if (indexPrevious != -1 && index < indexPrevious) {
                isSorted = false;
            }
            indexPrevious = index;
        }
        if (isSorted) {
            return str;
        }
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                char current = (char)('a' + i);
                for (int j = 0; j < dict[i]; j++) {
                    builder.append(current);
                }
            }
        }
        return builder.toString();
    }
}
