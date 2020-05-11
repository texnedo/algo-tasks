package com.texnedo;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        DivideTwoIntegers divideTwoIntegers = new DivideTwoIntegers();
        System.out.print(divideTwoIntegers.divide(20, 5));
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean signPositive = false;
        if ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            signPositive = true;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int result = 0;
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) >> 2;
            int multi = 0;
            for (int i = 0; i < mid; i++) {
                multi += divisor;
                //TODO - fix this algorithm
                if (multi >= dividend) {
                    result = i + 1;
                    break;
                }
            }
            if (multi > dividend) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return signPositive ? result : -result;
    }
}
