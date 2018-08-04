package com.texnedo;

import java.util.Arrays;

public class SelectMaxToken {
    public static void main(String[] args) {
        String [] words = {"Apple", "Melon", "Orange", "Watermelon"};
        String [] parts = {"a", "mel", "lon", "el", "An"};
        String [] result = new String [words.length];
        //Arrays.sort(parts);
        for (int i = 0; i < words.length; i++) {
            String lastFoundPart = null;
            int lastFoundStart = -1;
            for (int j = 0; j < parts.length; j++) {
                int foundStart = words[i].indexOf(parts[j]);
                if (foundStart < 0) {
                    continue;
                }
                boolean update = false;
                if (lastFoundStart == -1) {
                    update = true;
                } else if (lastFoundPart.length() < parts[j].length()) {
                    update = true;
                } else if (lastFoundPart.length() == parts[j].length() && lastFoundStart > foundStart) {
                    update = true;
                }
                if (update) {
                    lastFoundStart = foundStart;
                    lastFoundPart = parts[j];
                }
            }
            if (lastFoundStart == -1) {
                result[i] = words[i];
            } else {
                StringBuilder builder = new StringBuilder(words[i].length() + 2);
                if (lastFoundStart > 0) {
                    builder.append(words[i], 0, lastFoundStart);
                }
                builder.append('[')
                        .append(lastFoundPart)
                        .append(']');
                int lastFoundEnd = lastFoundStart + lastFoundPart.length() - 1;
                if (lastFoundEnd < words[i].length() - 1) {
                    builder.append(words[i], lastFoundEnd + 1, words[i].length());
                }
                result[i] = builder.toString();
            }
        }
        //
        System.out.println(Arrays.toString(result));
    }
}
