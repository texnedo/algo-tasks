package com.texnedo;

public class IntSquareRoot {

    public static void main(String[] args) {
        IntSquareRoot cmp = new IntSquareRoot();
        System.out.println(cmp.sqrt(10240 * 10240));
    }

    public int sqrt(int x) {
        int start = 0;
        int end = x;
        int closestToX = 0;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            long powered = ((long) middle) * ((long) middle);
            if (powered > Integer.MAX_VALUE) {
                end = middle - 1;
            } else if (powered == x) {
                return middle;
            } else if (powered < x) {
                if (closestToX <= 0) {
                    closestToX = middle;
                } else {
                    int oldDist = Math.abs(x - closestToX * closestToX);
                    int newDist = Math.abs(x - (int)powered);
                    if (newDist < oldDist) {
                        closestToX = middle;
                    } else {
                        return closestToX;
                    }
                }
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return closestToX;
    }
}
