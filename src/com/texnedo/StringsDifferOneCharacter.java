package com.texnedo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StringsDifferOneCharacter {
    public boolean differByOne(String[] dict) {
        HashMap<String, List<Integer>> index = new HashMap<>();
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < dict[i].length(); j++) {
                char current = dict[i].charAt(j);
                String hash = String.format("%s_%d", current, j);
                List<Integer> existing = index.get(hash);
                if (existing == null) {
                    existing = new LinkedList<>();
                    index.put(hash, existing);
                }
                existing.add(i);
            }
        }
        int countAtLeastTwoLength = 0;
        for (List<Integer> indexes : index.values()) {
            if (indexes.size() >= 2) {
                countAtLeastTwoLength++;
                if (countAtLeastTwoLength >= dict[0].length() - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
