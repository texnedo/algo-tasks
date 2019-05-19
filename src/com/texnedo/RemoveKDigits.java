package com.texnedo;

import java.util.PriorityQueue;
import java.util.Stack;

public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits removeKDigits = new RemoveKDigits();
        //System.out.println(removeKDigits.removeKdigits("1432219", 3));
        System.out.println(removeKDigits.removeKdigits("89", 1));
    }

    public String removeKdigits(String num, int k) {
        StringBuilder result = new StringBuilder(num.length() - k);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
           char current = num.charAt(i);
           int value = Character.getNumericValue(current);
           while (!stack.empty() && k > 0 && stack.peek() > value) {
               stack.pop();
               k--;
           }
           stack.push(value);
        }
        while (!stack.empty() && k > 0) {
            stack.pop();
            k--;
        }
        boolean hasFirstNonZero = false;
        for (int value : stack) {
            if (!hasFirstNonZero && value == 0) {
                continue;
            }
            hasFirstNonZero = true;
            result.append(value);
        }
        if (result.length() == 0) {
            return "0";
        }
        return result.toString();
    }
}
