package com.texnedo;

import java.util.HashMap;
import java.util.Objects;

public class PalindromicSubstrings {
    public static void main(String[] args) {
        PalindromicSubstrings substrings = new PalindromicSubstrings();
        System.out.println(substrings.countSubstrings("aada"));
        System.out.println(substrings.countSubstrings("aaa"));
    }

    private static class Range {
        final int start;
        final int end;

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start &&
                    end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public int countSubstrings(String s) {
        HashMap<Range, Integer> cache = new HashMap<>();
        return countSubstrings(s, 0, s.length() - 1, cache);
    }

    public int countSubstrings(String s, int from, int to, HashMap<Range, Integer> cache) {
        Range range = new Range(from, to);
        Integer existing = cache.get(range);
        if (existing != null) {
            return 0;
        }
        if (from == to) {
            cache.put(range, 1);
            return 1;
        }
        int total = 0;
        if (isPlalindrome(s, from, to)) {
            total++;
        }
        for (int i = to - from; i >= 1; i--) {
            int start = from;
            int end = start + i - 1;
            while (end <= to) {
                total += countSubstrings(s, start, end, cache);
                start++;
                end++;
            }
        }
        cache.put(range, total);
        return total;
    }

    public boolean isPlalindrome(String s, int start, int end) {
        System.out.println(s.substring(start, end + 1));
        int diff = end - start + 1;
        if (diff % 2 == 0) {
            int border = start + diff / 2;
            for (int i = start, j = end; i < border; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
        } else {
            int middle = (end - start) / 2 + start;
            for (int i = start, j = end; i < middle; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
