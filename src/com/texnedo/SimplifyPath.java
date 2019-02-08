package com.texnedo;

import java.util.LinkedList;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath path = new SimplifyPath();
        //System.out.println(path.simplifyPath("/a//b////c/d//././/.."));
        System.out.println(path.simplifyPath("/../"));
    }

    public String simplifyPath(String path) {
        LinkedList<String> parts = new LinkedList<>();
        int prevSlash = 0;
        for (int i = 0; i < path.length(); i++) {
            char current = path.charAt(i);
            if (current == '/') {
                int length = i - prevSlash;
                if (length < 0) {
                    throw new IllegalStateException();
                }
                if (length != 0) {
                    String token = path.substring(prevSlash + 1, i);
                    processToken(parts, token);
                }
                prevSlash = i;
            }
        }
        if (prevSlash < path.length() - 1) {
            processToken(parts, path.substring(prevSlash + 1));
        }
        StringBuilder builder = new StringBuilder();
        while (!parts.isEmpty()) {
            String token = parts.removeLast();
            builder.append('/');
            builder.append(token);
        }
        if (builder.length() == 0) {
            builder.append('/');
        }
        return builder.toString();
    }

    private void processToken(LinkedList<String> parts, String token) {
        if (!token.isEmpty()) {
            if (token.equals("..")) {
                if (!parts.isEmpty()) {
                    parts.pop();
                }
            } else if (!token.equals(".")) {
                parts.push(token);
            }
        }
    }
}
