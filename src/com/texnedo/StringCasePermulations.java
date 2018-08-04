package com.texnedo;

import java.util.LinkedList;
import java.util.List;

public class StringCasePermulations {
    public static void main(String[] args) {
        StringCasePermulations permulations = new StringCasePermulations();
        System.out.println(permulations.letterCasePermutation("a1b2"));
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new LinkedList<>();
        letterCasePermutationInternal(new StringBuilder(s), 0, result);
        return result;
    }

    public void letterCasePermutationInternal(StringBuilder s, int start, List<String> result) {
        for (int i = start; i < s.length(); i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                continue;
            }
            if (Character.isLowerCase(current)) {
                s.setCharAt(i, Character.toUpperCase(current));
            } else {
                s.setCharAt(i, Character.toLowerCase(current));
            }
            letterCasePermutationInternal(s, i + 1, result);
            s.setCharAt(i, current);
        }
        result.add(s.toString());
    }
}
