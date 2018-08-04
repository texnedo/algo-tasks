package com.texnedo;

import java.util.LinkedList;
import java.util.List;

public class GenerateParanthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        generateParenthesisInternal(n, n, result, null);
        return result;
    }

    public static void generateParenthesisInternal(int opened, int closed,
                                            List<String> result,
                                            StringBuilder current) {
        if (opened == 0 && closed == 0) {
            if (current != null) {
                result.add(current.toString());
            }
            return;
        }
        if (opened == 0) {
            if (current == null) {
                return;
            }
            int count = closed;
            while(count > 0) {
                current.append(')');
                count--;
            }
            result.add(current.toString());
            return;
        }
        StringBuilder openedBranch = current == null ? new StringBuilder() : new StringBuilder(current);
        openedBranch.append('(');
        generateParenthesisInternal(opened - 1, closed, result, openedBranch);
        if (opened != closed) {
            StringBuilder closedBranch = current == null ? new StringBuilder() : new StringBuilder(current);
            closedBranch.append(')');
            generateParenthesisInternal(opened, closed - 1, result, closedBranch);
        }
    }
}
