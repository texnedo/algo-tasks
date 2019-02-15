package com.texnedo;

import java.util.Stack;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        BackspaceStringCompare compare = new BackspaceStringCompare();
        System.out.println(compare.backspaceCompare("ab#c", "ad#c"));
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        initStack(S, stackS);
        initStack(T, stackT);
        if (stackS.size() != stackT.size()) {
            return false;
        }
        while (!stackS.isEmpty()) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        return true;
    }

    private void initStack(String S, Stack<Character> stackS) {
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            if (current == '#') {
                if (!stackS.empty()) {
                    stackS.pop();
                }
            } else {
                stackS.push(current);
            }
        }
    }
}
