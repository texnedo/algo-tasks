package com.texnedo;

public class RemoveAllAdjacentDuplicatesInString {
    /**
     * abbaca
     * */
    public String removeDuplicates(String S) {
        StringBuilder builder = new StringBuilder(S.length());
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            if (builder.length() == 0) {
                builder.append(current);
            } else {
                char last = builder.charAt(builder.length() - 1);
                if (last == current) {
                    builder.deleteCharAt(builder.length() - 1);
                } else {
                    builder.append(current);
                }
            }
        }
        return builder.toString();
    }
}
