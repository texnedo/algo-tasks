package com.texnedo;

import java.util.*;

public class ReorganizeString {
    public static void main(String[] args) {
        ReorganizeString str = new ReorganizeString();
        System.out.println(str.reorganizeString("vvvvvlo"));
    }

    public String reorganizeString(String s) {
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
        PriorityQueue<Map.Entry<Character, Integer>> ordered = new PriorityQueue<>(
                freqs.size(),
                (o1, o2) -> -Integer.compare(o1.getValue(), o2.getValue())
        );
        ordered.addAll(freqs.entrySet());
        StringBuilder builder = new StringBuilder(s.length());
        while (builder.length() < s.length()) {
            if (builder.length() == 0) {
                Map.Entry<Character, Integer> top = ordered.poll();
                if (top == null || top.getValue() == 0) {
                    return "";
                }
                builder.append(top.getKey());
                freqs.put(top.getKey(), top.getValue() - 1);
                ordered.add(top);
            } else {
                Character previous = builder.charAt(builder.length() - 1);
                Map.Entry<Character, Integer> top = ordered.poll();
                if (top == null || top.getValue() == 0) {
                    return "";
                }
                if (top.getKey() != previous) {
                    builder.append(top.getKey());
                    freqs.put(top.getKey(), top.getValue() - 1);
                } else {
                    Map.Entry<Character, Integer> next = ordered.poll();
                    if (next == null || next.getValue() == 0) {
                        return "";
                    }
                    builder.append(next.getKey());
                    freqs.put(next.getKey(), next.getValue() - 1);
                    ordered.add(next);
                }
                ordered.add(top);
            }
        }
        return builder.toString();
    }
}
