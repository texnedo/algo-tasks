package com.texnedo;

public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        DeleteOperationForTwoStrings deletes = new DeleteOperationForTwoStrings();
        System.out.println(deletes.minDistance("sea", "eat"));
    }

    public int minDistance(String word1, String word2) {
        LongestCommonSubsequenceLength common = new LongestCommonSubsequenceLength();
        int length = common.lcsLength(word1, word2);
        return (word1.length() - length) + (word2.length() - length);
    }
}
