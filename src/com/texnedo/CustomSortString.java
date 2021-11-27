package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class CustomSortString {
    public String customSortString(String order, String s) {
        int[] compare = new int[26];
        for (int i = 0; i < order.length(); i++) {
            int index = order.charAt(i) - 'a';
            compare[index] = i;
        }
        Character[] resultData = new Character[s.length()];
        for (int i = 0; i < resultData.length; i++) {
            resultData[i] = s.charAt(i);
        }
        Arrays.sort(resultData, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                char c1 = o1;
                char c2 = o2;
                int i1 = c1 - 'a';
                int i2 = c2 - 'a';
                return Integer.compare(compare[i1], compare[i2]);
            }
        });
        StringBuilder result = new StringBuilder(s);
        for (int i = 0; i < resultData.length; i++) {
            result.append(resultData[i]);
        }
        return result.toString();
    }
}
