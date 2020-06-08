package com.texnedo;

public class RemovePalindromicSubsequences {
    public static void main(String[] args) {
        RemovePalindromicSubsequences subsequences = new RemovePalindromicSubsequences();
        System.out.println(subsequences.removePalindromeSub("ababb"));
    }

    public int removePalindromeSub(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 0) {
            return 0;
        }
        return removePalindromeSub2(s, 0, s.length() - 1);
    }

    private int removePalindromeSub(String s, int start, int end) {
        if (isPalindrom(s, start, end)) {
            return 1;
        }
        int left = removePalindromeSub(s, start, end - 1);
        int right = removePalindromeSub(s, start + 1, end);
        return Math.min(left, right) + 1;
    }

    private int removePalindromeSub2(String s, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (isPalindrom(s, start, end)) {
            return 1;
        }
        int windowSize = end - start - 1;
        while (windowSize > 0) {
            int windowStart = start;
            int windowEnd = windowStart + windowSize;
            while (windowEnd <= end) {
                if (isPalindrom(s, windowStart, windowEnd)) {
                    return 1 + removePalindromeSub2(s, start, windowStart - 1)
                            + removePalindromeSub2(s, windowEnd + 1, end);
                }
                windowStart++;
                windowEnd = windowStart + windowSize;
            }
            windowSize--;
        }
        return 0;
    }

    private boolean isPalindrom(String s, int start, int end) {
        int i = start, j = end;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
