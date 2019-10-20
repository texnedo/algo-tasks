package com.texnedo;

import java.util.Stack;

public class DecodeString {
    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString("3[a]2[bc]"));
        System.out.println(decodeString.decodeString("3[a2[c]]"));
        System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
    }

    private static class StartToken {
        final int count;
        final int index;
        final StringBuilder text = new StringBuilder();

        private StartToken(int count, int index) {
            this.count = count;
            this.index = index;
        }
    }

    public String decodeString(String s) {
        final Stack<StartToken> tokens = new Stack<>();
        final StringBuilder commonText = new StringBuilder();
        int digitStart = -1;
        for (int i = 0; i < s.length(); i++) {
            final char current = s.charAt(i);
            if (!Character.isDigit(current) && current != '[' && current != ']') {
                if (tokens.empty()) {
                    commonText.append(current);
                } else {
                    tokens.peek().text.append(current);
                }
            } else if (Character.isDigit(current) && digitStart == -1) {
                digitStart = i;
            } else if (!Character.isDigit(current) && digitStart != -1 && current == '[') {
                final String digit = s.substring(digitStart, i);
                final StartToken token = new StartToken(Integer.parseInt(digit), i);
                tokens.push(token);
                digitStart = -1;
            }
            if (current == ']') {
                if (tokens.empty()) {
                    throw new IllegalArgumentException();
                }
                final StartToken lastToken = tokens.pop();
                StringBuilder parent;
                if (tokens.empty()) {
                    parent = commonText;
                } else {
                    parent = tokens.peek().text;
                }
                for (int j = 0; j < lastToken.count; j++) {
                    parent.append(lastToken.text);
                }
            }
        }
        return commonText.toString();
    }
}
