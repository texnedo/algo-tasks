package com.texnedo;

import java.util.*;

public class SortCharactersByFrequency {

    public static void main(String[] args) {
        SortCharactersByFrequency frequency = new SortCharactersByFrequency();
        System.out.println(frequency.frequencySort("cccaaa"));
    }

    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        HashMap<Character, Integer> freqs = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            Integer count = freqs.get(ch);
            if (count == null) {
                freqs.put(ch, 1);
            } else {
                freqs.put(ch, ++count);
            }
        }
        List<Map.Entry<Character, Integer>> ordered = new ArrayList<>(freqs.size());
        ordered.addAll(freqs.entrySet());
        Collections.sort(ordered, (o1, o2) -> -Integer.compare(o1.getValue(), o2.getValue()));
        StringBuilder builder = new StringBuilder(s.length());
        for (Map.Entry<Character, Integer> entry : ordered) {
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }
}
