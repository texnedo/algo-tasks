package com.texnedo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> grouped = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            String hash = getBasicShift(strings[i]);
            List<String> existing = grouped.get(hash);
            if (existing == null) {
                existing = new LinkedList<>();
                grouped.put(hash, existing);
            }
            existing.add(strings[i]);
        }
        return new LinkedList<>(grouped.values());
    }

    private String getBasicShift(String original) {
        StringBuilder result = new StringBuilder(original.length());
        for (int i = 0; i < original.length(); i++) {
            int diff = original.charAt(i) - original.charAt(0);
            if (diff < 0) {
                diff += 26;
            }
            result.append(diff).append("_");
        }
        return result.toString();
    }
}
