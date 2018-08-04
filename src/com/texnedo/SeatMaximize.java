package com.texnedo;

public class SeatMaximize {
    public static void main(String[] args) {
        int [] data = {1, 0, 0, 1};
        System.out.println(maxDistToClosest(data));
    }

    public static int maxDistToClosest(int[] seats) {
        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int previous = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                int length = previous == -1 ? i : (i - previous) / 2;
                if (length > maxLength) {
                    maxLength = length;
                    maxStart = previous;
                    maxEnd = i;
                }
                previous = i;
            }
        }
        if (previous != -1 && previous != seats.length - 1) {
            int length = seats.length - 1 - previous;
            if (length > maxLength) {
                maxLength = length;
                maxStart = previous;
                maxEnd = seats.length;
            }
        }
        if (maxStart == -1) {
            return maxLength;
        }
        if (maxEnd == seats.length) {
            return maxLength;
        }
        int middle = maxStart + (maxEnd - maxStart) / 2;
        return Math.min(middle - maxStart, maxEnd - middle);
    }
}
