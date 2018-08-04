package com.texnedo;

public class ReverseInt {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        if (x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return 0;
        }
        int [] digits = new int[100];
        int num = x;
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        int count = 0;
        while(num != 0) {
            int rest = num % 10;
            num = (num - rest) / 10;
            digits[count] = rest;
            count++;
        }
        long reversed = 0;
        for (int i = count - 1, j = 0; i >= 0; i--, j++) {
            reversed += Math.pow(10, j) * digits[i];
            if (reversed > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return negative ? (int)(-reversed) : (int)(reversed);
    }
}
