package com.texnedo;

import java.util.Arrays;

public class LeftRotation {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(rotLeft(a, 3)));
    }

    static int[] rotLeft(int[] a, int d) {
        if (a == null || a.length == 0) {
            return a;
        }
        int shift = d % a.length;
        int[] tail = new int[shift];
        for (int i = 0, j = 0; i < tail.length; i++) {
            tail[i] = a[i];
        }
        for (int i = shift; i < a.length; i++) {
            a[i - shift] = a[i];
        }
        for (int i = a.length - shift, j = 0; i < a.length; i++, j++) {
            a[i] = tail[j];
        }
        return a;
    }
}
