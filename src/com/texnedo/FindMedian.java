package com.texnedo;

public class FindMedian {
    public static void main(String[] args) {
        FindMedian median = new FindMedian();
        System.out.println(median.median(323, 10000, 233));
    }

    int median(int a, int b, int c) {
        int min = Math.min(Math.min(a, b), c);
        int max = Math.max(Math.max(a, b), c);
        if (a != min && a != max) {
            return a;
        }
        if (b != min && b != max) {
            return b;
        }
        return c;
    }
}
