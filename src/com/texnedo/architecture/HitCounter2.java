package com.texnedo.architecture;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HitCounter2 {
    //private static final long WINDOW_SIZE = TimeUnit.MINUTES.toSeconds(5);
    private static final long WINDOW_SIZE = TimeUnit.SECONDS.toSeconds(30);
    private final class HitNode {
        long count;
        long timestamp;

        public HitNode(long count, long timestamp) {
            this.count = count;
            this.timestamp = timestamp;
        }
    }
    private final LinkedList<HitNode> hitWindow = new LinkedList<>();
    private final LinkedList<HitNode> reuseBuffer = new LinkedList<>();

    void hit(long timestamp) {
        if (hitWindow.isEmpty()) {
            hitWindow.addLast(createHitNode(1, timestamp));
            return;
        }
        final HitNode lastNode = hitWindow.peekLast();
        if (lastNode.timestamp == timestamp) {
            lastNode.count++;
        } else {
            final long diff = timestamp - lastNode.timestamp;
            if (diff > WINDOW_SIZE) {
                hitWindow.clear();
                lastNode.timestamp = timestamp;
                lastNode.count = 1;
                hitWindow.addLast(lastNode);
            } else {
                removeExpired(timestamp);
                hitWindow.addLast(createHitNode(1, timestamp));
            }
        }
    }

    long getHits(long timestamp) {
        removeExpired(timestamp);
        long sum = 0;
        for (HitNode node : hitWindow) {
            sum += node.count;
        }
        return sum;
    }

    private HitNode createHitNode(long count, long timestamp) {
        if (reuseBuffer.isEmpty()) {
            return new HitNode(1, timestamp);
        }
        final HitNode reused = reuseBuffer.poll();
        reused.count = count;
        reused.timestamp = timestamp;
        return reused;
    }

    private void removeExpired(long timestamp) {
        HitNode firstNode = hitWindow.getFirst();
        while (firstNode.timestamp < timestamp - WINDOW_SIZE) {
            reuseBuffer.offer(hitWindow.removeFirst());
            firstNode = hitWindow.getFirst();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final HitCounter2 counter = new HitCounter2();
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
