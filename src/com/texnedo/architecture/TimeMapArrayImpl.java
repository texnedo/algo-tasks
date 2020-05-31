package com.texnedo.architecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class TimeMapArrayImpl implements TimeMap {
    static class Entry implements Comparable <Entry>{
        final int timestamp;
        final String value;

        Entry(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }

        @Override
        public int compareTo(Entry o) {
            return Integer.compare(timestamp, o.timestamp);
        }
    }

    private final HashMap<String, List<Entry>> map = new HashMap<>();
    private final int initialArraySize;

    /**
     * Initialize your data structure here.
     */
    public TimeMapArrayImpl(int initialArraySize) {
        this.initialArraySize = initialArraySize;
    }

    @Override
    public void set(String key, String value, int timestamp) {
        List<Entry> times = map.computeIfAbsent(key, k -> new ArrayList<>(initialArraySize));
        times.add(new Entry(timestamp, value));
    }

    @Override
    public String get(String key, int timestamp) {
        List<Entry> times = map.get(key);
        if (times == null || times.isEmpty()) {
            return "";
        }
        int index = Collections.binarySearch(times, new Entry(timestamp, null));
        if (index >= 0) {
            return times.get(index).value;
        }
        index = -index - 2;
        if (index < 0) {
            return "";
        }
        return times.get(index).value;
    }

    @Override
    public void clear() {
        map.clear();
    }
}
