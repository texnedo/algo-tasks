package com.texnedo;

public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }

    public int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 31; i++) {
            count += (x & mask) == (y & mask) ? 0 : 1;
            mask <<= 1;
        }
        return count;
    }
}
