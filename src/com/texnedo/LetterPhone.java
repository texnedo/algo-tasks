package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LetterPhone {
    public static void main(String[] args) {
        LetterPhone phone = new LetterPhone();
        System.out.println(phone.letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        String[] keyboard = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<StringBuilder> builders = letterCombinationsInternal(digits, keyboard, 0);
        List<String> result = new ArrayList<>(builders.size());
        for (StringBuilder builder : builders) {
            result.add(builder.toString());
        }
        return result;
    }

    private List<StringBuilder> letterCombinationsInternal(String digits, String[] keyboard, int start) {
        if (start >= digits.length()) {
            return Collections.emptyList();
        }
        Character current = digits.charAt(start);
        if (!Character.isDigit(current)) {
            throw new IllegalArgumentException();
        }
        int number = Character.getNumericValue(current);
        String keys = keyboard[number];
        if (start == digits.length() - 1) {
            if (keys.length() == 0) {
                return Collections.emptyList();
            }
            return buildCombinations(keys);
        }
        List<StringBuilder> combinations = letterCombinationsInternal(digits, keyboard, start + 1);
        if (keys.length() == 0) {
            return combinations;
        }
        if (combinations.size() == 0) {
            return buildCombinations(keys);
        }
        List<StringBuilder> result = new ArrayList<>(combinations.size() * keys.length());
        for (int i = 0; i < keys.length(); i++) {
            for (StringBuilder item : combinations) {
                StringBuilder extendedItem = new StringBuilder(item);
                extendedItem.insert(0, keys.charAt(i));
                result.add(extendedItem);
            }
        }
        return result;
    }

    private List<StringBuilder> buildCombinations(String keys) {
        List<StringBuilder> result = new ArrayList<>(keys.length());
        for (int j = 0; j < keys.length(); j++) {
            StringBuilder builder = new StringBuilder(1);
            builder.append(keys.charAt(j));
            result.add(builder);
        }
        return result;
    }


}
