package com.texnedo;

public class VowelStrings {
    private static final int[] alphabet = new int[26];
    static {
        //'a', 'e', 'i', 'o', and 'u'
        alphabet['a' - 'a'] = 1;
        alphabet['e' - 'a'] = 1;
        alphabet['i' - 'a'] = 1;
        alphabet['o' - 'a'] = 1;
        alphabet['u' - 'a'] = 1;
    }
    private static boolean isWovel(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);
        if (alphabet[first - 'a'] == 1 && alphabet[last - 'a'] == 1) {
            return true;
        }
        return false;
    }

    public int vowelStrings(String[] words, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (isWovel(words[i])) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        VowelStrings str = new VowelStrings();
        String[] data = {"hey","aeo","mu","ooo","artro"};
        System.out.println(str.vowelStrings(data, 1, 4));
    }
}
