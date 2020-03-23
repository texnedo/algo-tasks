package com.texnedo;

public class PowerOfThree {
    /**
     * 3 * 3 = 9
     * 3 * 3 * 3 = 27
     * 3 * 3 * 3 * 3 = 81
     * */
    public boolean isPowerOfThree(int n) {
        if (n <= 0 || n == 2) {
            return false;
        }
        if (n == 1 || n == 3) {
            return true;
        }
        return isPowerOfThree(n / 3);
    }
}
