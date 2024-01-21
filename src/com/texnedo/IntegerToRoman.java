package com.texnedo;

import jnr.ffi.annotations.In;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * */
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }
        int[] numbers = new int[]      {1000, 900, 500, 400,  100, 90,   50,  40,   10,   9,    5,   4,    1};
        String[] values = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        int value = num;
        for (int i = 0; i < numbers.length; i++) {
            int count = value / numbers[i];
            if (count != 0) {
                builder.ensureCapacity(count);
                for (int j = 0; j < count; j++) {
                    builder.append(values[i]);
                }
                value = value % numbers[i];
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman roman = new IntegerToRoman();
        System.out.println(roman.intToRoman(1989));
    }
}
