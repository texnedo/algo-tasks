package com.texnedo.architecture;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.ServiceCheck;
import com.timgroup.statsd.StatsDClient;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HitCounter {
    //private static final long OFFSET_WINDOW = TimeUnit.MINUTES.toSeconds(5);
    private static final long OFFSET_WINDOW = TimeUnit.SECONDS.toSeconds(30);
    private final LinkedList<Long> timestamps = new LinkedList<>();

    private static final StatsDClient statsd = new NonBlockingStatsDClient(
            "algo-tasks",                          /* prefix to any stats; may be null or empty string */
            "localhost",                        /* common case: localhost */
            8125,                                 /* port */
            new String[] {"test222"}            /* Datadog extension: Constant tags, always applied */,
            e -> System.out.println(e.toString())
    );

    void hit(long timestamp) {
        if (timestamp < 0) {
            throw new IllegalArgumentException();
        }
        if (timestamps.size() > 0 && timestamp < timestamps.getLast()) {
            throw new IllegalArgumentException();
        }
        timestamps.addLast(timestamp);
        if (timestamps.size() > 1) {
            final long diff = timestamp - timestamps.getFirst();
            if (diff >= OFFSET_WINDOW) {
                timestamps.removeFirst();
            }
        }
    }

    long getHits(long timestamp) {
        if (timestamps.size() == 0) {
            return 0;
        }
        final long diff = timestamp - timestamps.getLast();
        if (diff >= OFFSET_WINDOW) {
            timestamps.clear();
            return 0;
        }
        while (timestamps.size() > 0) {
            final long diffFirst = timestamp - timestamps.getFirst();
            if (diffFirst < OFFSET_WINDOW) {
                break;

            }
            timestamps.removeFirst();
        }
        return timestamps.size();
    }

    public static void main(String[] args) throws InterruptedException {
        final ServiceCheck sc = ServiceCheck
                .builder()
                .withName("hitcounter.check")
                .withStatus(ServiceCheck.Status.OK)
                .build();
        statsd.serviceCheck(sc); /* Datadog extension: send service check status */
        final HitCounter counter = new HitCounter();
        final Random rnd = new Random();
        while (true) {
            statsd.incrementCounter("hitcounter.hit");
            counter.hit(System.currentTimeMillis() / 1000);
            final int randValue = rnd.nextInt(1000);
            if (randValue % 10 == 0) {
                long count = counter.getHits(System.currentTimeMillis() / 1000);
                System.out.println(count);
                statsd.recordGaugeValue("hitcounter.count", count);
                Thread.sleep(2);
            }
        }
     }
}
