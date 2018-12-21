package com.texnedo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning palindrome = new PalindromePartitioning();
        String s = "aab";
        System.out.println(palindrome.partition(s));
    }

    public List<List<String>> partition(String s) {
        return partitionInternal(s, 0, s.length() - 1);
    }

    public List<List<String>> partitionInternal(String s, int start, int end) {
        final List<List<String>> partitions = new LinkedList<>();
        for (int i = start; i <= end; i++) {
            if (isPalindrome(s, start, i)) {
                final String first = s.substring(start, i + 1);
                final List<List<String>> second = partitionInternal(s, i + 1, end);
                if (second.isEmpty()) {
                    final LinkedList<String> single = new LinkedList<>();
                    single.add(first);
                    second.add(single);
                } else {
                    for (List<String> sub : second) {
                        ((LinkedList<String>)sub).addFirst(first);
                    }
                }
                partitions.addAll(second);
            }
        }
        return partitions;
    }

    public boolean isPalindrome(String s, int start, int end) {
        int j = end;
        for (int i = start; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
