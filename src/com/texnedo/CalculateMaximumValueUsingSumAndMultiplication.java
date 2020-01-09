package com.texnedo;

public class CalculateMaximumValueUsingSumAndMultiplication {
    public static void main(String[] args) {
        CalculateMaximumValueUsingSumAndMultiplication calc =
                new CalculateMaximumValueUsingSumAndMultiplication();
        System.out.println(calc.maxSum("01231"));
        System.out.println(calc.maxSum("891"));
    }

    public int maxSum(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException();
        }
        Integer sum = null;
        for (int i = 0; i < value.length(); i++) {
            int current = Character.getNumericValue(value.charAt(i));
            if (sum == null) {
                sum = current;
            } else if (sum < 2 || current < 2) {
                sum += current;
            } else {
                sum *= current;
            }
        }
        return sum;
    }
}
