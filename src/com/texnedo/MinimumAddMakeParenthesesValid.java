package com.texnedo;

import java.util.Stack;

public class MinimumAddMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int errorCount = 0;
        Stack<Integer> opened = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                opened.push(i);
            } else {
                if (!opened.isEmpty()) {
                    opened.pop();
                } else {
                    errorCount++;
                }
            }
        }
        return errorCount + opened.size();
    }
}
