package com.texnedo.architecture;

import java.util.*;

public class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeMap map1 = new TimeMapTreeImpl();
        long diff1 = runTest(map1);
        map1.clear();
        System.gc();
        TimeMap map2 = new TimeMapArrayImpl(1024);
        long diff2 = runTest(map2);
        map1.clear();
        System.gc();
        System.out.println(diff1);
        System.out.println(diff2);
    }

    public static long runTest(TimeMap map) {
        Random rnd = new Random();
        long startTs = System.nanoTime();
        int timestamp = 0;
        for (int i = 0; i < 1000000; i++) {
            String key = "METRIC_" + rnd.nextInt(100);
            String value = "METRIC_VALUE_" + rnd.nextInt(10000);
            System.out.println(
                    String.format(Locale.US, "Inserted %s : %s (%d)", key, value, timestamp)
            );
            map.set(key, value, timestamp++);
            int randomTimestamp = rnd.nextInt(timestamp);
            String result = map.get(key, randomTimestamp);
            System.out.println(
                    String.format(Locale.US, "Stored %s : %s (%d)", key, result, randomTimestamp)
            );
        }
        long endTs = System.nanoTime();
        return endTs - startTs;
    }

}
