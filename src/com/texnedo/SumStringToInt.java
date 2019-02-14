package com.texnedo;

public class SumStringToInt {
    private int sumStringNumbers(String part1, String part2) {
        int total = 0;
        int index1 = part1.length() - 1;
        int index2 = part2.length() - 1;
        int carry = 0;
        int count = 0;
        while (index1 >= 0 || index2 >= 0) {
            char c1 = index1 >= 0 ? part1.charAt(index1) : '0';
            char c2 = index2 >= 0 ? part2.charAt(index2) : '0';
            int sum = Character.getNumericValue(c1) + Character.getNumericValue(c2) + carry;
            if (sum > 10) {
                carry = sum - 10;
            } else if (sum == 10) {
                carry = 1;
            } else {
                carry = 0;
            }
            sum = sum < 10 ? sum : sum - 10;
            total += sum * Math.pow(10, count);
            index1--;
            index2--;
            count++;
        }
        if (carry != 0) {
            total += carry * Math.pow(10, count);
        }
        return total;
    }
}
