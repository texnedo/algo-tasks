package com.texnedo;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(lvDistance("abd", "abd"));
    }

    public static int lvDistance(String s1, String s2) {
        return lvDistanceInternal(s1, s2, 0, 0);
    }

    private static int lvDistanceInternal(String s1, String s2, int idx1, int idx2) {
        if (s1 == null || s1.length() == 0 || idx1 == s1.length()) {
            return s2 != null ? s2.length() - idx2 : 0;
        }
        if (s2 == null || s2.length() == 0 || idx2 == s2.length()) {
            return s1.length() - idx1;
        }
        int cost = s1.charAt(idx1) == s2.charAt(idx2) ? 0 : 1;
        int costToRemove1 = lvDistanceInternal(s1, s2, idx1 + 1, idx2) + 1;
        int costToRemove2 = lvDistanceInternal(s1, s2, idx1, idx2 + 1) + 1;
        int costNoChange = lvDistanceInternal(s1, s2, idx1 + 1, idx2 + 1) + cost;
        return Math.min(costNoChange, Math.min(costToRemove1, costToRemove2));
    }
}
