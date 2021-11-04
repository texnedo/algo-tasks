package com.texnedo;

import java.util.Stack;

public class MinimumRemoveMakeValidParentheses {
    public static void main(String[] args) {
        MinimumRemoveMakeValidParentheses parentheses = new MinimumRemoveMakeValidParentheses();
        System.out.println(parentheses.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(parentheses.minRemoveToMakeValid("))(("));
        System.out.println(parentheses.minRemoveToMakeValid("(a(b(c)d)"));
    }

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> mustBeRemovedIndexes = new Stack<>();
        Stack<Integer> openedIndexes = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                openedIndexes.push(i);
            } else if (current == ')') {
                if (openedIndexes.isEmpty()) {
                    mustBeRemovedIndexes.add(i);
                } else {
                    openedIndexes.pop();
                }
            }
        }
        mustBeRemovedIndexes.addAll(openedIndexes);
        StringBuilder builder = new StringBuilder(s);
        while (!mustBeRemovedIndexes.isEmpty()) {
            int indexToRemove = mustBeRemovedIndexes.pop();
            builder.replace(indexToRemove, indexToRemove + 1, "");
        }
        return builder.toString();
    }
}
