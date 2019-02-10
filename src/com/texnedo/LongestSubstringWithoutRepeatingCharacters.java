package com.texnedo;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> dict = new HashMap<>();
        int maxLength = 0;
        int curLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!dict.containsKey(ch)) {
                dict.put(ch, i);
            } else {
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                int start = i - curLength;
                int end = dict.get(ch);
                for (int j = start; j <= end; j++) {
                    char chSkip = s.charAt(i);
                    dict.remove(chSkip);
                    curLength--;
                }
                dict.put(ch, i);
            }
            curLength++;
        }
        if (curLength > maxLength) {
            maxLength = curLength;
        }
        return maxLength;
    }
}
