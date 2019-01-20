package com.texnedo;

import java.util.List;

public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("12312312123123123", "0"));
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
                num2 == null || num2.length() == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder[] intermediates = new StringBuilder[num2.length()];
        int maxIntermediateLength = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            char c2 = num2.charAt(i);
            if (!Character.isDigit(c2)) {
                throw new IllegalArgumentException();
            }
            int n2 = Character.getNumericValue(c2);
            if (n2 == 0 && num2.length() == 1) {
                return "0";
            }
            int carry = 0;
            intermediates[i] = new StringBuilder();
            int shift = num2.length() - 1 - i;
            for (int s = 0; s < shift; s++) {
                intermediates[i].append('0');
            }
            for (int j = num1.length() - 1; j >= 0; j--) {
                char c1 = num1.charAt(j);
                if (!Character.isDigit(c1)) {
                    throw new IllegalArgumentException();
                }
                int n1 = Character.getNumericValue(c1);
                if (n1 == 0 && num1.length() == 1) {
                    return "0";
                }
                int multiplied = n1 * n2 + carry;
                int result = multiplied % 10;
                carry = multiplied / 10;
                intermediates[i].insert(0, result);
            }
            if (carry != 0) {
                intermediates[i].insert(0, carry);
            }
            if (intermediates[i].length() > maxIntermediateLength) {
                maxIntermediateLength = intermediates[i].length();
            }
        }
        int carry = 0;
        StringBuilder product = new StringBuilder();
        for (int i = 0; i < maxIntermediateLength; i++) {
            int summed = carry;
            for (int j = 0; j < intermediates.length; j++) {
                int offset = intermediates[j].length() - 1 - i;
                if (offset >= 0) {
                    char c = intermediates[j].charAt(offset);
                    int n = Character.getNumericValue(c);
                    summed += n;
                }
            }
            int result = summed % 10;
            carry = summed / 10;
            product.insert(0, result);
        }
        if (carry != 0) {
            product.insert(0, carry);
        }
        return product.toString();
    }
}
