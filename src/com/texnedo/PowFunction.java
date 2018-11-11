package com.texnedo;

public class PowFunction {
    public static void main(String[] args) {
        PowFunction powFunction = new PowFunction();
        System.out.println(powFunction.myPow(-1, 9));
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (Math.abs(x) == 1) {
            return n % 2 == 0 ? 1 : x;
        }
        if (n == Integer.MIN_VALUE) {
            return 0;
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        if (n % 2 == 0) {
            double power = myPow(x, n / 2);
            return power * power;
        }
        double power = myPow(x, (n - 1) / 2);
        return x * power * power;
    }
}
