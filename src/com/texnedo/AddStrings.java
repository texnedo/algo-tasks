package com.texnedo;

import java.util.LinkedList;
import java.util.Locale;

public class AddStrings {
    public static void main(String[] args) {
        AddStrings sum = new AddStrings();
        System.out.println(sum.addStrings("1835", "2386"));
    }

    public String addStrings(String num1, String num2) {
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        int it1 = num1.length() - 1;
        int it2 = num2.length() - 1;
        while(it1 >= 0 || it2 >= 0) {
            int val1 = 0;
            int val2 = 0;
            if (it1 >= 0) {
                char current = num1.charAt(it1);
                val1 = current - '0';
            }
            if (it2 >= 0) {
                char current = num2.charAt(it2);
                val2 = current - '0';
            }
            int sum = val1 + val2 + carry;
            carry = 0;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            }
            result.addFirst(sum);
            System.out.println(String.format(Locale.US, "Current: %d + %d = %s", val1, val2, result));
            System.out.println("Carry: " + carry);
            it1--;
            it2--;
        }
        if (carry != 0) {
            result.addFirst(carry);
        }
        System.out.println("Current final: " + result);
        StringBuilder builder = new StringBuilder();
        for (Integer val : result) {
            builder.append(val);
        }
        return builder.toString();
    }
}
