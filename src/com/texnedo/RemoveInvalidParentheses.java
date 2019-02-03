package com.texnedo;

import java.util.*;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses parentheses = new RemoveInvalidParentheses();
        //System.out.println(parentheses.removeInvalidParentheses("()())()"));
        //System.out.println(parentheses.removeInvalidParentheses("(a)())()"));
        //System.out.println(parentheses.removeInvalidParentheses("()"));
        //System.out.println(parentheses.removeInvalidParentheses(")("));
        System.out.println(parentheses.removeInvalidParentheses("(((k()(("));
    }

    public List<String> removeInvalidParentheses(String s) {
        if (isValid(s)) {
            return Collections.singletonList(s);
        }
        HashSet<String> result = new HashSet<>();
        LinkedList<String> currentLevel = new LinkedList<>();
        LinkedList<String> nextLevel = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        currentLevel.add(s);
        while (!currentLevel.isEmpty()) {
            while (!currentLevel.isEmpty()) {
                String source = currentLevel.pop();
                if (visited.contains(source)) {
                    continue;
                }
                visited.add(source);
                for (int i = 0; i < source.length(); i++) {
                    char current = source.charAt(i);
                    if (current == '(' || current == ')') {
                        StringBuilder builder = new StringBuilder(source.length() - 1);
                        if (i > 0) {
                            builder.append(source, 0, i);
                        }
                        if (i != source.length() - 1) {
                            builder.append(source, i + 1, source.length());
                        }
                        String option = builder.toString();
                        if(isValid(option)) {
                            result.add(option);
                        } else {
                            nextLevel.add(option);
                        }
                    }
                }
            }
            if (!result.isEmpty()) {
                break;
            }
            LinkedList<String> tmp = currentLevel;
            currentLevel = nextLevel;
            nextLevel = tmp;
        }
        return new ArrayList<>(result);
    }

    private boolean isValid(String s) {
        LinkedList<Character> balanceCheck = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                balanceCheck.push(current);
            } else if (current == ')') {
                if (balanceCheck.isEmpty()) {
                    return false;
                }
                balanceCheck.pop();
            }
        }
        return balanceCheck.isEmpty();
    }
}
