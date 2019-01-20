package com.texnedo;

import java.util.Stack;

public class NestedListWeightSum {
    public static void main(String[] args) {
        NestedListWeightSum sum = new NestedListWeightSum();
        System.out.println(sum.calculate("[[1,1],2,[1,1]]"));
        System.out.println(sum.calculate("[1,[4,[6]]]"));
    }

    public int calculate(String expr) {
        Stack<Character> braces = new Stack<>();
        int sum = 0;
        for (int i = 0; i < expr.length(); i++) {
            char current = expr.charAt(i);
            if (current == '[') {
                braces.push(current);
            } else if (current == ']') {
                braces.pop();
            } else if (current == ',') {
                continue;
            } else {
                if (!Character.isDigit(current)) {
                    throw new IllegalArgumentException();
                }
                if (braces.size() == 0) {
                    throw new IllegalArgumentException();
                }
                sum += braces.size() * Character.getNumericValue(current);
            }
        }
        if (braces.size() != 0) {
            throw new IllegalArgumentException();
        }
        return sum;
    }
}
