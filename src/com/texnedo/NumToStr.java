package com.texnedo;

public class NumToStr {
    static String [] numWordsLessThan20 = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static String [] numWordsTens = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static String [] numWordsBigger = {"hundred", "thousand", "million", "billion", "trillion", "quadrillion"};
    static long [] bigger = {100, 1000, 1000000, 1000000000, 1000000000000L, 1000000000000000L};

    public static void main(String[] args) {
        long number = 3934453498676L;
        System.out.println(numToString(number));
    }

    public static String numToString(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("value less than zero");
        }
        if (number < 20) {
            return numWordsLessThan20[(int) number];
        }
        if (number < 100) {
            int rest = (int) (number % 10);
            int base = (int) (number / 10);
            return numWordsTens[base - 2] + " " + numWordsLessThan20[rest];
        }
        for (int i = 0; i < bigger.length; i++) {
            if (number > bigger[i]) {
                continue;
            }
            long rest = number % bigger[i - 1];
            int base = (int) (number / bigger[i - 1]);
            return numToString(base) + " " + numWordsBigger[i - 1] + " " + numToString(rest);
        }
        throw new IllegalArgumentException("too big value");
    }
}
