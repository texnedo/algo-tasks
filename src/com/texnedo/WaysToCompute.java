package com.texnedo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class WaysToCompute {
    public static void main(String[] args) {
        WaysToCompute compute = new WaysToCompute();
        System.out.println(compute.diffWaysToCompute(" 2 * 3 - 4 * 5 "));
    }

    public List<Integer> diffWaysToCompute(String input) {
        return diffWaysToComputeInternal(input, 0, input.length() - 1);
    }

    public List<Integer> diffWaysToComputeInternal(String input, int start, int end) {
        if (start > end) {
            return Collections.emptyList();
        }
        if (isDigit(input, start, end)) {
            return Collections.singletonList(Integer.parseInt(input.substring(start, end + 1)));
        }
        List<Integer> result = new LinkedList<>();
        boolean noOperator = true;
        for (int i = start; i <= end; i++) {
            if (isOperator(input.charAt(i))) {
                List<Integer> left = diffWaysToComputeInternal(input, start, i - 1);
                List<Integer> right = diffWaysToComputeInternal(input, i + 1, end);
                for (Integer argLeft : left) {
                    for (Integer argRight : right) {
                        result.add(compute(argLeft, argRight, input.charAt(i)));
                    }
                }
                noOperator = false;
            }
        }
        if (noOperator) {
            Integer digitStart = null;
            Integer digitEnd = null;
            for (int i = start; i <= end; i++) {
                if (Character.isDigit(input.charAt(i))) {
                    if (digitStart == null) {
                        digitStart = i;
                        digitEnd = i;
                    } else {
                        digitEnd = i;
                        break;
                    }
                }
            }
            if (digitStart != null) {
                result.add(Integer.parseInt(input.substring(digitStart, digitEnd + 1)));
            }
        }
        return result;
    }

    boolean isDigit(String input, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*';
    }

    int compute(int left, int right, char operator) {
        if (operator == '-') {
            return left - right;
        } else if (operator == '+') {
            return left + right;
        } else {
            return left * right;
        }
    }
}
