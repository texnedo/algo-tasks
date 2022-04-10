package com.texnedo;

public class StringtoInteger {
    public static void main(String[] args) {
        StringtoInteger atoi = new StringtoInteger();
        System.out.println(atoi.myAtoi("123"));
        System.out.println(atoi.myAtoi("   -123 words"));
        System.out.println(atoi.myAtoi("   - words 123 words"));
        System.out.println(atoi.myAtoi("   -00123  "));
        System.out.println(atoi.myAtoi("2147483647"));
        System.out.println(atoi.myAtoi("2147483649"));
        System.out.println(atoi.myAtoi("-2147483648"));
        System.out.println(atoi.myAtoi("-2147483649"));
        System.out.println(atoi.myAtoi("-91283472332"));
    }

    public int myAtoi(String s) {
        int result = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '+') {
                sign = 1;
                break;
            } else if (current == '-') {
                sign = -1;
                break;
            } else if (Character.isDigit(current)) {
                break;
            } else if (current != ' ') {
                return 0;
            }
        }
        boolean hasNumberStarted = false;
        int multiplier = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                hasNumberStarted = true;
                int currentDigit = (current - '0');
                int maxMultiplier = Integer.MAX_VALUE / currentDigit;
                if (multiplier > maxMultiplier) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                int addition = multiplier * (current - '0');
                if (sign == 1) {
                    int diff = Integer.MAX_VALUE - result;
                    if (diff < addition) {
                        result = Integer.MAX_VALUE;
                        break;
                    }
                } else {
                    int diff = Integer.MIN_VALUE - result;
                    if (diff > -addition) {
                        result = Integer.MIN_VALUE;
                        break;
                    }
                }
                result += sign * addition;
                multiplier *= 10;
            } else {
                if (hasNumberStarted) {
                    if (current != ' ' && current != '+' && current != '-') {
                        return 0;
                    }
                    return result;
                }
            }
        }
        return result;
    }
}
