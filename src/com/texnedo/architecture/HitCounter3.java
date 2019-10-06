package com.texnedo.architecture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HitCounter3 {
    private static final int WINDOW_SIZE = (int)TimeUnit.SECONDS.toSeconds(30);
    private long[] timestamps = new long[WINDOW_SIZE];
    private long[] hits = new long[WINDOW_SIZE];

    /**
     * [][][][][][][][][][]
     * */
    void hit(long timestamp) {
        int slot = (int) (timestamp % WINDOW_SIZE);
        if (timestamps[slot] == timestamp) {
            hits[slot]++;
        } else {
            timestamps[slot] = timestamp;
            hits[slot] = 1;
        }
    }

    long getHits(long timestamp) {
        long sum = 0;
        for (int i = 0; i < WINDOW_SIZE; i++) {
            long diff = timestamp - timestamps[i];
            if (diff < WINDOW_SIZE) {
                sum += hits[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        final HitCounter3 counter = new HitCounter3();
        final Random rnd = new Random();
        while (true) {
            counter.hit(System.currentTimeMillis() / 1000);
            final int randValue = rnd.nextInt(1000);
            if (randValue % 10 == 0) {
                final long count = counter.getHits(System.currentTimeMillis() / 1000);
                System.out.println(count);
                Thread.sleep(2);
            }
        }
    }
}
