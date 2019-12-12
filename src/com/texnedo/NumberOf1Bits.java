package com.texnedo;

public class NumberOf1Bits {
    public static void main(String[] args) {
        NumberOf1Bits bits = new NumberOf1Bits();
        System.out.println(bits.hammingWeight(5));
        System.out.println(bits.hammingWeight(6));
        System.out.println(bits.hammingWeight(7));

        System.out.println(bits.hammingWeightOptimized(5));
        System.out.println(bits.hammingWeightOptimized(6));
        System.out.println(bits.hammingWeightOptimized(7));
    }

    public int hammingWeight(int n) {
        int count = 0;
        int check = 1;
        while (n != 0) {
            int result = n & check;
            if (result == check) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public int hammingWeightOptimized(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
