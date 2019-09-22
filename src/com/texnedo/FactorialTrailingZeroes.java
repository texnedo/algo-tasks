package com.texnedo;

public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        FactorialTrailingZeroes fac = new FactorialTrailingZeroes();
        int value = 2147483647;
        //System.out.println(fac.factorial(value));
        System.out.println(fac.trailingZeroes(value));
    }

    public long factorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

    public int trailingZeroes(int n) {
        int count = 0;
        int power = 1;
        long fiveInPower = 0;
        do {
            fiveInPower = (long) Math.pow(5, power);
            count += n / fiveInPower;
            power++;
        } while (fiveInPower <= n);
        return count;
    }
}
