package com.texnedo;

public class ReverseWords {
    public void reverseString(StringBuilder s, int start, int end) {
        if (s == null) {
            return;
        }
        if (s.length() == 0 || s.length() == 1) {
            return;
        }
        if (start < 0 || end >= s.length()) {
            throw new IllegalArgumentException();
        }
        int middle = start + (end - start) / 2;
        for (int i = start, j = end; i <= middle; i++, j--) {
            char c = s.charAt(i);
            s.setCharAt(i, s.charAt(j));
            s.setCharAt(j, c);
        }
    }

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return s;
        }
        String ts = s.trim();
        if (ts.length() == 0 || ts.length() == 1) {
            return ts;
        }
        StringBuilder sb = new StringBuilder(s.trim());
        reverseString(sb, 0, sb.length() - 1);
        System.out.println(sb.toString());
        int start = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == ' ') {
                reverseString(sb, start, i - 1);
                int j = i;
                int si = i;
                while(i < sb.length() && sb.charAt(++j) == ' ') {
                    i++;
                }
                if (si != i) {
                    sb.replace(si, i, "");
                }
                i = si;
                start = i + 1;
            }
        }
        reverseString(sb, start, sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "  the   sky  is      blue  ";
        System.out.println(s);
        ReverseWords r = new ReverseWords();
        System.out.println(r.reverseWords(s));
    }
}
