package com.texnedo;

public class CountAndSay {
    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(5));
    }

    public String countAndSay(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        if (n == 1) {
            return "1";
        }
        String previous = countAndSay(n - 1);
        StringBuilder result = new StringBuilder(previous.length());
        char prevItem = previous.charAt(0);
        int sameCount = 1;
        for (int i = 1; i < previous.length(); i++) {
            char currItem = previous.charAt(i);
            if (currItem == prevItem) {
                sameCount++;
            } else {
                result.append(Integer.toString(sameCount));
                result.append(prevItem);
                sameCount = 1;
                prevItem = currItem;
            }
        }
        if (sameCount != 0) {
            result.append(Integer.toString(sameCount));
            result.append(prevItem);
        }
        return result.toString();
    }
}
