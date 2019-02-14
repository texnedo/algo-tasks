package com.texnedo;

public class SumTwoNumbers {

    public static void main(String[] args) {
        SumTwoNumbers numbers = new SumTwoNumbers();
        System.out.println(numbers.sumNumbers("234.105", "100.0054"));
        System.out.println(numbers.sumNumbers("234", "100.0054"));
        System.out.println(numbers.sumNumbers("234.4", "100"));
        System.out.println(numbers.sumNumbers("234", "100"));
        System.out.println(numbers.sumNumbers("234", "0"));
        System.out.println(numbers.sumNumbers("234", null));
    }

    /**
     * Take two positive numbers as strings, and return the sum of them.
     * E.g. "3.14" + "0.9" => "4.04".
     */
    public String sumNumbers(String arg1, String arg2) {
        if (arg1 == null && arg2 == null) {
            throw new IllegalArgumentException();
        }
        if (arg1 == null) {
            return arg2;
        }
        if (arg2 == null) {
            return arg1;
        }
        String[] tokens1 = arg1.split("\\.");
        String[] tokens2 = arg2.split("\\.");
        if (tokens1.length > 2 || tokens2.length > 2 ||
                tokens1.length == 0 || tokens2.length == 0) {
            throw new IllegalArgumentException();
        }
        if (tokens1.length == 1 && tokens2.length == 1) {
            return Integer.toString(intFromString(arg1) + intFromString(arg2));
        }
        if (tokens1.length == 2 && tokens2.length == 2) {
            int sumFirst = intFromString(tokens1[0]) + intFromString(tokens2[0]);
            float sumSecond = floatFromString(tokens1[1]) + floatFromString(tokens2[1]);
            return Float.toString(sumFirst + sumSecond);
        }
        if (tokens1.length == 2) {
            int sumFirst = intFromString(tokens1[0]) + intFromString(arg2);
            float sumSecond = floatFromString(tokens1[1]);
            return Float.toString(sumFirst + sumSecond);
        }
        int sumFirst = intFromString(arg1) + intFromString(tokens2[0]);
        float sumSecond = floatFromString(tokens2[1]);
        return Float.toString(sumFirst + sumSecond);
    }

    private float floatFromString(String number) {
        float total = 0;
        for (int i = number.length() - 1; i >=0; i--) {
            char cDigit = number.charAt(i);
            if (!Character.isDigit(cDigit)) {
                throw new IllegalArgumentException();
            }
            int digit = Character.getNumericValue(cDigit);
            total += digit * Math.pow(10, -(i + 1));
        }
        return total;
    }

    private int intFromString(String number) {
        int total = 0;
        for (int i = number.length() - 1, count = 0; i >=0; i--, count++) {
            char cDigit = number.charAt(i);
            if (!Character.isDigit(cDigit)) {
                throw new IllegalArgumentException();
            }
            int digit = Character.getNumericValue(cDigit);
            total += digit * Math.pow(10, count);
        }
        return total;
    }
}
