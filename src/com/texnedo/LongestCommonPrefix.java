package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] arr = {"flo", "flower", ""};
        System.out.println(longestCommonPrefix2(arr));
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int pos = 0;
        while (true) {
            int[] dict = new int[26];
            boolean noCommon = false;
            for (String str : strs) {
                if (pos >= str.length()) {
                    noCommon = true;
                    break;
                }
                char ch = str.charAt(pos);
                int idx = ch - 'a';
                dict[idx]++;
            }
            if (noCommon) {
                break;
            }
            for (int count : dict) {
                if (count != strs.length && count != 0) {
                    noCommon = true;
                    break;
                }
            }
            if (noCommon) {
                break;
            }
            pos++;
        }
        return strs[0].substring(0, pos);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefixInternal(Arrays.asList(strs), 0);
    }

    public static String longestCommonPrefixInternal(List<String> strs, int pos) {
        List<String>[] dict = new List[26];
        for (String str : strs) {
            if (pos < str.length()) {
                char ch = str.charAt(pos);
                int idx = ch - 'a';
                List<String> group = dict[idx];
                if (group == null) {
                    group = new ArrayList<>();
                    dict[idx] = group;
                }
                group.add(str);
            }
        }
        String maxPrefix = "";
        for (List<String> group : dict) {
            if (group != null && group.size() >= 2) {
                String prefix = group.get(0).charAt(pos) + longestCommonPrefixInternal(group, pos + 1);
                if (prefix.length() > maxPrefix.length()) {
                    maxPrefix = prefix;
                }
            }
        }
        return maxPrefix;
    }
}
