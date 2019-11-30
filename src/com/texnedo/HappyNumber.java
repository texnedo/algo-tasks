package com.texnedo;

import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber number = new HappyNumber();
        //System.out.println(number.isHappy(111111));
        System.out.println(number.isHappy(19));
        //System.out.println(number.isHappy(29));
    }

    public boolean isHappy(int n) {
        HashSet<Integer> values = new HashSet<>();
        return isHappyInternal(n, values);
    }

    private boolean isHappyInternal(int n, HashSet<Integer> values) {
        int sum = sumSquares(n);
        if (values.contains(sum)) {
            return false;
        }
        values.add(sum);
        if (sum == 1) {
            return true;
        }
        return isHappyInternal(sum, values);
    }

    private int sumSquares(int n) {
        int sum = 0;
        int div = 10;
        int current = n;
        while (current > 0) {
            int digit = current % div;
            sum += digit * digit;
            current -= digit;
            current /= div;
        }
        return sum;
    }
}
