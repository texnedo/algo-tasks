package com.texnedo;

import java.util.HashMap;
import java.util.Map;

public class MininumStringWindow {
    static class StringWindow {
        int start;
        int end;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            Integer value = target.get(ch);
            if (value == null) {
                target.put(ch, 1);
            } else {
                target.put(ch, value + 1);
            }
        }
        StringWindow min = null;
        StringWindow current = null;
        Map<Character, Integer> found = new HashMap<>(target);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer value = target.get(ch);
            if (value != null) {
                if (value > 1) {
                    target.put(ch, value - 1);
                } else {
                    found.remove(ch);
                }
                if (current == null) {
                    current = new StringWindow();
                    current.start = i;
                    current.end = i;
                } else {
                    current.end = i;
                }
            }
            if (found.isEmpty()) {
                found = new HashMap<>(target);
                if (min == null) {
                    min = current;
                } else {
                    int lengthCurrent = current.end - current.start;
                    int lengthMin = min.end - min.start;
                    if (lengthMin > lengthCurrent) {
                        min = current;
                    }
                }
                current = null;
                i--;
            }
        }
        if (min == null) {
            return "";
        }
        return s.substring(min.start, min.end + 1);
    }
}
