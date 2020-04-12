package com.texnedo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyHashMap {
    private static final int MAX_BUCKET_SIZE = 3;
    private static final int INIT_BUCKET_COUNT = 5;
    private static final long KEY_MASK = 0xFFFF0000;
    private static final long VALUE_MASK = 0x0000FFFF;
    private ArrayList<List<Long>> buckets = new ArrayList<>(INIT_BUCKET_COUNT);

    /**
     * Initialize your data structure here.
     * */
    public MyHashMap() {
        for (int i = 0; i < INIT_BUCKET_COUNT; i++) {
            buckets.add(new ArrayList<>(MAX_BUCKET_SIZE));
        }
    }

    /**
     * value will always be non-negative.
     * */
    public void put(int key, int value) {
        int bucketId = key % buckets.size();
        List<Long> bucket = buckets.get(bucketId);
        long composite = getComposite(key, value);
        for (int i = 0; i < bucket.size(); i++) {
            if (getKeyFromComposite(bucket.get(i)) == key) {
                bucket.set(i, composite);
                return;
            }
        }
        //TODO - find first available free item
        bucket.add(composite);
        checkBucketsAndRehash(bucket);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or -1 if this map contains no mapping for the key
     * */
    public int get(int key) {
        int bucketId = key % buckets.size();
        List<Long> bucket = buckets.get(bucketId);
        if (bucket.size() == 0) {
            return -1;
        }
        for (int i = 0; i < bucket.size(); i++) {
            long composite = bucket.get(i);
            if (getKeyFromComposite(composite) == key) {
                return getValueFromComposite(composite);
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if
     * this map contains a mapping for the key
     * */
    public void remove(int key) {
        int bucketId = key % buckets.size();
        List<Long> bucket = buckets.get(bucketId);
        if (bucket.size() == 0) {
            return;
        }
        for (int i = 0; i < bucket.size(); i++) {
            long composite = bucket.get(i);
            if (getKeyFromComposite(composite) == key) {
                //TODO - optimize removal using null items
                bucket.remove(i);
                return;
            }
        }
    }

    private long getComposite(int key, int value) {
        long result = 0;
        long resultLeft = ((long) key) << 32;
        long resultRight = ((long) value);
        result = resultLeft | resultRight;
        return result;
    }

    private int getKeyFromComposite(long value) {
        long result = (value & KEY_MASK) >> 32;
        return (int) result;
    }

    private int getValueFromComposite(long value) {
        long result = value & VALUE_MASK;
        return (int) result;
    }

    private void checkBucketsAndRehash(List<Long> currentBucket) {
        if (currentBucket.size() > MAX_BUCKET_SIZE) {
            int newSize = buckets.size() * 2;
            ArrayList<List<Long>> newBuckets = new ArrayList<>(newSize);
            for (int i = 0; i < newSize; i++) {
                newBuckets.add(new ArrayList<>(MAX_BUCKET_SIZE));
            }
            for (List<Long> bucket : buckets) {
                for (Long composite : bucket) {
                    int key = getKeyFromComposite(composite);
                    int bucketId = key % newBuckets.size();
                    List<Long> newBucket = newBuckets.get(bucketId);
                    newBucket.add(composite);
                }
            }
            buckets = newBuckets;
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        long comp1 = map.getComposite(10, 15);
        System.out.println(map.getKeyFromComposite(comp1));
        System.out.println(map.getValueFromComposite(comp1));
        map.put(10, 15);
        map.put(1, 3);
        map.put(6, 10);
        System.out.println(map.get(10));
        System.out.println(map.get(1));
        System.out.println(map.get(6));
        Random rnd = new Random();
        for (int i = 20; i < 100; i++) {
            map.put(i, rnd.nextInt(1000));
        }
        System.out.println(map.get(10));
        System.out.println(map.get(1));
        System.out.println(map.get(6));
    }
}
