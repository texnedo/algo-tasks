package com.texnedo;

import java.util.Stack;

public class BalancedBrackets {
    private static final char[] OPENING = {'(', '{', '['};
    private static final char[] CLOSING = {')', '}', ']'};

    public static void main(String[] args) {
        String test = "()(){[]}()";
        System.out.println(isBalanced(test));
        String test1 = "()([){[]}()";
        System.out.println(isBalanced(test1));
        String test2 = "{{)[](}}";
        System.out.println(isBalanced(test2));
    }

    static String isBalanced(String s) {
        if (s == null || s.isEmpty()) {
            return "YES";
        }
        if (s.length() % 2 != 0) {
            return "NO";
        }
        Stack<Character> balance = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (balance.empty()) {
                balance.push(current);
            } else {
                char previous = balance.peek();
                int result = isPairBracket(previous, current);
                if (result == -1) {
                    return "NO";
                }
                if (result == 1) {
                    balance.pop();
                } else {
                    balance.push(current);
                }
            }
        }
        return balance.empty() ? "YES" : "NO";
    }

    private static int isPairBracket(char first, char second) {
        int firstIndex = -1;
        for (int i = 0; i < OPENING.length; i++) {
            if (OPENING[i] == first) {
                firstIndex = i;
                break;
            }
        }
        if (firstIndex != -1) {
            return CLOSING[firstIndex] == second ? 1 : 0;
        }
        return -1;
    }
}
