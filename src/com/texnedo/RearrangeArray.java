package com.texnedo;

import java.util.Arrays;

public class RearrangeArray {
    public static void main(String[] args) {
        RearrangeArray array = new RearrangeArray();
        int[] data = {4, 0, 2, 1, 3};
        array.rearrange(data);
        System.out.println(Arrays.toString(data));

        int comb = encode(5, 7, 10);
        System.out.println(comb);
        System.out.println(decodeNum1(comb, 10));
        System.out.println(decodeNum2(comb, 10));

        int[] data1 = {4, 0, 2, 1, 3};
        array.rearrangeNoExtArray(data1);
        System.out.println(Arrays.toString(data1));
    }

    public void rearrange(int[] data) {
        int[] tmp = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            int current = data[i];
            tmp[i] = data[current];
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = tmp[i];
        }
    }

    public void rearrangeNoExtArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int current = data[i] >= 0 ?
                    data[i] : decodeNum1(-data[i], data.length);
            int updated = data[current] >=0 ?
                    data[current] : decodeNum1(-data[current], data.length);
            data[i] = -encode(current, updated, data.length);
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = decodeNum2(-data[i], data.length);
        }
    }

    private static int encode(int num1, int num2, int N) {
        return num1 + num2 * N;
    }

    private static int decodeNum1(int comb, int N) {
        return comb % N;
    }

    private static int decodeNum2(int comb, int N) {
        return (comb - decodeNum1(comb, N)) / N;
    }
}
