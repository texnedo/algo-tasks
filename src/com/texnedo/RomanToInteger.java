package com.texnedo;

public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger roman = new RomanToInteger();
        System.out.println(roman.romanToInt("IV"));
    }

    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentRoman = getRomanValue(s.charAt(i));
            if (i == s.length() - 1) {
                result += currentRoman;
            } else {
                int nextRoman = getRomanValue(s.charAt(i + 1));
                if (currentRoman < nextRoman) {
                    result += (nextRoman - currentRoman);
                    i++;
                } else {
                    result += currentRoman;
                }
            }
        }
        return result;
    }

    private int getRomanValue(char roman) {
        switch (roman) {
            case 'I' :
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException();
        }
    }
}
